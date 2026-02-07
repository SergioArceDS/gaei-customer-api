#--------------- STAGE 1: Build --------------------------
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

#Copiamos pom.xml para aprovechar cache
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

RUN ./mvnw dependency:go-offline

#Copiamos codigo fuente
COPY src src

#Compilamos
RUN ./mvnw clean package -DskipTests

#--------------- STAGE 2: Runtime --------------------------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

#Copiamos el .jar del stage 1
COPY --from=build /app/target/*.jar gaei-customer-1.0.0.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "gaei-customer-1.0.0.jar"]