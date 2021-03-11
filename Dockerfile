FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine as builder
ADD . /src
WORKDIR /src
RUN ./mvnw package -DskipTests

FROM alpine:3.13.1 as packager
RUN apk add --update --no-cache openjdk11-jdk openjdk11-jmods mysql
ENV JAVA_MINIMAL="/opt/java-minimal"
RUN /usr/lib/jvm/java-11-openjdk/bin/jlink \
    --verbose \
    --add-modules \
        java.base,java.sql,java.naming,java.desktop,java.management,java.security.jgss,java.instrument \
    --compress 2 --strip-debug --no-header-files --no-man-pages \
    --release-info="add:IMPLEMENTOR=radistao:IMPLEMENTOR_VERSION=radistao_JRE" \
    --output "$JAVA_MINIMAL"

FROM alpine:3.13.1
LABEL maintainer="Bobrov.da B0brov.da@yandex.ru"
ENV JAVA_HOME=/opt/java-minimal
ENV PATH="$PATH:$JAVA_HOME/bin"
COPY --from=packager "$JAVA_HOME" "$JAVA_HOME"
COPY --from=builder /src/target/test-0.0.1-*.jar app.jar
#RUN apk add --update --no-cache mysql
EXPOSE 8090
ENTRYPOINT ["java","-jar","/app.jar","sh"]
CMD ["-c" , "tail -f /dev/null"]

#https://github.com/vishnubob/wait-for-it/blob/master/wait-for-it.sh
#ENTRYPOINT ["./wait-for-it.sh", "db:3306", "--", "java", "-jar", "app.jar"]