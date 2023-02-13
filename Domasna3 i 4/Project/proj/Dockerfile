FROM openjdk:17-alpine

WORKDIR /Project/proj

COPY target/CIT-Restaurants.jar /Project/proj/CIT-Restaurants.jar

EXPOSE 9091

CMD ["java", "-jar", "CIT-Restaurants.jar"]