#!/bin/bash

function checkServiceByNameAndMessage() {
    name=$1
    message=$2
    docker-compose logs "$name" > "logs"
    string=$(cat logs)
    counter=0
    while [[ "$string" != *"$message"* ]]
    do
      printf "."
      docker-compose logs "$name" > "logs"
      string=$(cat logs)
      sleep 1
      counter=$((counter+1))
      if [ $counter -eq 200 ]; then
          echo "Failed after $counter tries! Cypress tests may fail!!"
          echo $string
          exit 1
      fi
    done
    counter=$((counter+1))
    echo "Succeeded $name Service after $counter tries!"
}

checkServiceByNameAndMessage fla_nginx 'test is successful'
