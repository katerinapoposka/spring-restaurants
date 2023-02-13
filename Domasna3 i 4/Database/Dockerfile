FROM postgres:12

ENV POSTGRES_DB=cit-restaurants
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=admin

EXPOSE 5432

COPY schema.sql /docker-entrypoint-initdb.d/
CMD ["postgres"]