b: build-npm build-maven
build: build-npm
	mvn clean install
build-npm:
	cd favourite-lyrics-gui && yarn install && yarn build
build-maven:
	mvn clean install -DskipTests
test:
	mvn test
test-maven:
	mvn test
local: no-test
	mkdir -p bin
no-test:
	mvn clean install -DskipTests
docker:
	docker-compose down
	docker-compose up -d --build --remove-orphans
docker-databases: stop local
build-images:
build-docker: stop no-test build-npm
	docker-compose up -d --build --remove-orphans
show:
	docker ps -a  --format '{{.ID}} - {{.Names}} - {{.Status}}'
docker-delete-idle:
	docker ps --format '{{.ID}}' -q | xargs docker rm
docker-delete: stop
	docker ps -a --format '{{.ID}}' -q | xargs docker stop
	docker ps -a --format '{{.ID}}' -q | xargs docker rm
docker-cleanup: docker-delete
	docker images -q | xargs docker rmi
docker-clean:
	docker-compose down -v
	docker-compose rm -svf
docker-clean-build-start: docker-clean b docker
docker-delete-apps: stop
docker-local:
	docker-compose up -d fla_postgres
prune-network: stop
	docker network prune
prune-all: stop
	docker ps -a --format '{{.ID}}' -q | xargs docker stop
	docker ps -a --format '{{.ID}}' -q | xargs docker rm
	docker network prune
	docker system prune --all
	docker builder prune
	docker system prune --all --volumes
stop:
	docker-compose down --remove-orphans
stop-all: stop
	docker-compose down --remove-orphans
	docker ps -a --format '{{.ID}}' -q | xargs docker stop
install:
	nvm install --lts
	nvm use --lts
