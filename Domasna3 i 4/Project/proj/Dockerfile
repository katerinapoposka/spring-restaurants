FROM openjdk:17-alpine

WORKDIR /Project/proj

COPY target/CIT-Restaurants.jar /Project/proj/CIT-Restaurants.jar

EXPOSE 8080

CMD ["java", "-jar", "CIT-Restaurants.jar"]