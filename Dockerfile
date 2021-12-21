FROM maven:3-openjdk-11 AS BUILDER
WORKDIR /app
COPY ./BanqueApi .
RUN mvn -e -B -DskipTests clean package

FROM openjdk:11
COPY --from=BUILDER /app/target/revolutmiage-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java","-jar","revolutmiage-0.0.1-SNAPSHOT.jar"]