FROM eclipse-temurin:21-jre

RUN mkdir -p /newaim-module-product-biz
WORKDIR /newaim-module-product-biz

COPY ./target/newaim-module-product-biz.jar app.jar

## 设置 TZ 时区
## 设置 JAVA_OPTS 环境变量，可通过 docker run -e "JAVA_OPTS=" 进行覆盖
ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms512m -Xmx512m"

EXPOSE 38100

CMD java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar app.jar
