cypress-open:
	yarn && npm run cypress
cypress-electron:
	docker-compose up -d e2e-electron
	docker-compose logs -f e2e-electron
cypress-chrome:
	docker-compose up -d e2e-chrome
	docker-compose logs -f e2e-chrome
cypress-firefox:
	docker-compose up -d e2e-firefox
	docker-compose logs -f e2e-firefox
cypress-edge:
	docker-compose up -d e2e-edge
	docker-compose logs -f e2e-edge

