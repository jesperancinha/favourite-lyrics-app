#!/bin/bash

function checkServiceByNameAndMessage() {
    name=$1
    message=$2
    docker-compose logs "$name" > "logs"
    string=$(cat logs)
    counter=0
    echo -n "Starting service $name "
    while [[ "$string" != *"$message"* ]]
    do
      echo -e -n "\e[93m-\e[39m"
      docker-compose logs "$name" > "logs"
      string=$(cat logs)
      sleep 1
      counter=$((counter+1))
      if [ $counter -eq 200 ]; then
          echo "Failed after $counter tries! Cypress tests may fail!!"
          echo "$string"
          exit 1
      fi
    done
    counter=$((counter+1))
    echo -e "\e[92m Succeeded starting $name Service after $counter tries!\e[39m"
}

checkServiceByNameAndMessage fla_postgres 'database system is ready to accept connections'
checkServiceByNameAndMessage fla_backend 'Started LyricsDemoApplicationLauncher'
checkServiceByNameAndMessage fla_nginx 'test is successful'
