#!/bin/bash

sed -i 's/${FLA_BACKEND}'"/${FLA_BACKEND}/g" /etc/nginx/conf.d/default.conf

nginx -t

nginx

tail -f /dev/null
