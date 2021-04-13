FROM openjdk:11-jre-slim

EXPOSE 8082

RUN mkdir /app

COPY build/libs/*.jar /app/pr-test.jar

ENTRYPOINT ["java","-jar","/app/pr-test.jar"]