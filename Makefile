b: build-npm build-maven
build: build-npm
	mvn clean install
build-npm:
	cd favourite-lyrics-gui && yarn && yarn build
build-maven:
	mvn clean install -DskipTests
test:
	mvn test
test-maven:
	mvn test
local: no-test
	mkdir -p bin
no-test: build-npm
	mvn clean install -DskipTests
docker:
	docker-compose down
	docker-compose up -d --build --remove-orphans
docker-databases: stop
	docker-compose up -d fla-postgres
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
	docker-compose up -d fla-postgres
docker-action:
	docker-compose -f docker-compose.yml up -d --build --remove-orphans
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
	curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.0/install.sh | bash
fla-wait:
	bash fla_wait.sh
dcup-light: dcd
	docker-compose up -d fla-postgres
dcup: dcd
	docker-compose up -d --build --remove-orphans
dcup-full-action: dcd docker-clean-build-start fla-wait
dcup-full: dcd docker-clean docker-action fla-wait
dcd: dc-migration
	docker-compose down
cypress-open:
	cd e2e && yarn && npm run cypress:open:electron
cypress-electron:
	cd e2e && make cypress-electron
cypress-chrome:
	cd e2e && make cypress-chrome
cypress-firefox:
	cd e2e && make cypress-firefox
cypress-edge:
	cd e2e && make cypress-edge
update:
	git pull
	npm install -g npm-check-updates
	cd favourite-lyrics-gui && npx browserslist --update-db && ncu -u && yarn
refresh-nginx:
	docker-compose stop fla-nginx
	docker-compose build fla-nginx
	docker-compose up -d fla-nginx
	bash fla_nginx_wait.sh
version-status:
	mvn versions:display-dependency-updates
version-update-maven:
	mvn versions:use-next-releases
	mvn versions:use-latest-releases
	mvn versions:use-releases
local-pipeline: dcd docker-clean b docker-action fla-wait cypress-electron
revert-deps-cypress-update:
	if [ -f  e2e/docker-composetmp.yml ]; then rm e2e/docker-composetmp.yml; fi
	if [ -f  e2e/packagetmp.json ]; then rm e2e/packagetmp.json; fi
	git checkout e2e/docker-compose.yml
	git checkout e2e/package.json
deps-cypress-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/cypressUpdateOne.sh | bash
deps-plugins-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/pluginUpdatesOne.sh | bash -s -- $(PARAMS)
deps-java-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/javaUpdatesOne.sh | bash
deps-update: update
deps-quick-update: deps-cypress-update deps-plugins-update deps-java-update
accept-prs:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/acceptPR.sh | bash
update-repo-prs:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/update-all-repo-prs.sh | bash
dc-migration:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/setupDockerCompose.sh | bash
