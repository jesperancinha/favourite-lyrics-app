FROM openjdk:17-slim-buster

WORKDIR /root

ENV LANG=C.UTF-8

COPY entrypoint.sh /root

COPY target/favourite-lyrics-starter.jar /root/favourite-lyrics-starter.jar

ENTRYPOINT ["/root/entrypoint.sh"]
