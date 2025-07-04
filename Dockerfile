FROM maven:3.9.4-eclipse-temurin-21-alpine AS build
COPY . /home/app/projetofinaljavaspring
RUN mvn -v
RUN mvn -f /home/app/projetofinaljavaspring/pom.xml clean package

FROM alpine/java:21-jdk
COPY --from=build /home/app/projetofinaljavaspring/target/*.jar /usr/local/lib/projetofinaljavaspring.jar
VOLUME /tmp
EXPOSE 80:8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/projetofinaljavaspring.jar"]
