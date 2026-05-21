# Etapa 1: Compilar a aplicação (Build)
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Rodar a aplicação (Imagem corrigida e atualizada)
FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
