FROM eclipse-temurin:21-jre

RUN mkdir -p /newaim-module-system-biz

WORKDIR /newaim-module-system-biz

COPY ./target/newaim-module-system-biz.jar app.jar

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms512m -Xmx512m"

EXPOSE 38081

CMD java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar app.jar


