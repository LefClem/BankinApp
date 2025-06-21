# Utilise une image Maven avec Java 21
FROM maven:3.9.6-eclipse-temurin-21

# Répertoire de travail dans le conteneur
WORKDIR /app

# Copie les fichiers Maven
COPY pom.xml .
COPY src ./src

# Compile et package le projet (avec ou sans les tests)
RUN mvn clean package -DskipTests

# Expose le port de Spring Boot
EXPOSE 8080

# Lance l'application
CMD ["java", "-jar", "target/BankinApp-0.0.1-SNAPSHOT.jar"]
