FROM eclipse-temurin

WORKDIR app

# copy the source files
COPY .mvn ./.mvn
COPY mvnw ./mvnw
COPY pom.xml ./pom.xml
COPY src ./src

# ./mvnw spring-boot:run
CMD ["./mvnw", "spring-boot:run"]