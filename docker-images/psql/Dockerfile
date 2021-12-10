FROM postgres:11.5

COPY init-scripts/create-multiple-postgresql-databases.sh /docker-entrypoint-initdb.d/create-multiple-postgresql-databases.sh

COPY postgresql.conf /etc/postgresql/postgresql.conf

RUN mkdir -p /data

RUN chmod 700 /data

RUN chown postgres:postgres /data

WORKDIR /root

EXPOSE 5432

