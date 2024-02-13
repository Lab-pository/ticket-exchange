FROM amazoncorretto:17
VOLUME /tmp
ARG JAR_FILE=./configuration/build/libs/*.jar
COPY ${JAR_FILE} ticket-lounge.jar
ENTRYPOINT ["java","-jar","/ticket-lounge.jar"]
