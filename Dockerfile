FROM openjdk:21-jdk

ARG PROFILE
ENV PROFILE ${PROFILE}

ARG MONGO_URI
ENV MONGO_URI ${MONGO_URI}

ARG LOGIN_URL
ENV LOGIN_URL ${LOGIN_URL}

ARG REDIS_HOST
ENV REDIS_HOST ${REDIS_HOST}

ARG SECRET
ENV SECRET ${SECRET}

ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} application.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "application.jar"]

