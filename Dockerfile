FROM adoptopenjdk:11-jre-hotspot

ARG JAR_FILE=infra/build/libs/infra.jar
COPY ${JAR_FILE} reading-is-good.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/reading-is-good.jar"]