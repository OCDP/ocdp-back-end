FROM openjdk:8-alpine
MAINTAINER EAD_SAUDE
RUN apk update && apk add bash
RUN mkdir -p /opt/app
ENV API_EAD_SAUDE /opt/app
COPY ./target/ocd-api-1.0.0-SNAPSHOT.jar $API_EAD_SAUDE
WORKDIR  $API_EAD_SAUDE
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod","ocd-api-1.0.0-SNAPSHOT.jar"]