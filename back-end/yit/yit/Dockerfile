FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY yit.jar /app/yit.jar

COPY map_a.csv /app/map/map_a.csv

EXPOSE 8080

CMD ["java", "-jar", "yit.jar"]