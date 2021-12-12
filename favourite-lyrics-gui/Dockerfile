FROM nginx

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

RUN mv /etc/nginx/conf.d/default.conf /etc/nginx/conf.d/default.conf.disabled

COPY nginx/default.conf /etc/nginx/conf.d/default.conf

COPY nginx/nginx.conf /etc/nginx/nginx.conf

COPY nginx/entrypoint.sh /usr/local/bin/

COPY /build /usr/share/nginx/html

ENTRYPOINT ["entrypoint.sh"]

EXPOSE 8080
