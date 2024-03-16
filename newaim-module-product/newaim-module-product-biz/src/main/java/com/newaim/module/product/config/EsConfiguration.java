package com.newaim.module.product.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import jakarta.annotation.Resource;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

@Configuration
public class EsConfiguration {

    @Resource
    private ElasticSearchProperties properties;

    private HttpHost[] toHttpHost() {
        if (!StringUtils.hasLength(properties.getHost())) {
            throw new RuntimeException("invalid elasticsearch configuration");
        }

        String[] hostArray = properties.getHost().split(",");
        HttpHost[] httpHosts = new HttpHost[hostArray.length];
        HttpHost httpHost;
        for (int i = 0; i < hostArray.length; i++) {
            String[] strings = hostArray[i].split(":");
            httpHost = new HttpHost(strings[0], Integer.parseInt(strings[1]), "https");
            httpHosts[i] = httpHost;
        }

        return httpHosts;
    }

    @Bean
    public ElasticsearchClient clientByPasswd() throws Exception {
        ElasticsearchTransport transport = getElasticsearchTransport(properties.getUsername(), properties.getPassword(), properties.getCrt(), toHttpHost());
        return new ElasticsearchClient(transport);
    }

    private static SSLContext buildSSLContext(String crt) {
        //通过文件读取证书
        SSLContext sslContext = null;
        try {
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            Certificate trustedCa;
            try (InputStream is = new FileInputStream(crt)) {
                trustedCa = factory.generateCertificate(is);
            }
            KeyStore trustStore = KeyStore.getInstance("pkcs12");
            trustStore.load(null, null);
            trustStore.setCertificateEntry("ca", trustedCa);
            SSLContextBuilder sslContextBuilder = SSLContexts.custom()
                    .loadTrustMaterial(trustStore, null);
            sslContext = sslContextBuilder.build();
        } catch (CertificateException | IOException | KeyStoreException | NoSuchAlgorithmException |
                 KeyManagementException e) {

        }

        return sslContext;
    }

    private static ElasticsearchTransport getElasticsearchTransport(String username, String passwd, String crt, HttpHost... hosts) {
        // 账号密码的配置
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, passwd));

        // 自签证书的设置，并且还包含了账号密码
        RestClientBuilder.HttpClientConfigCallback callback = httpClientBuilder -> {
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            httpClientBuilder.setSSLContext(buildSSLContext(crt));
            httpClientBuilder.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);
            return httpClientBuilder;
        };
        // 用builder创建RestClient对象
        RestClient client = RestClient
                .builder(hosts)
                .setHttpClientConfigCallback(callback)
                .build();

        return new RestClientTransport(client, new JacksonJsonpMapper());
    }

}
