# Favourite lyrics app

---

[![Twitter URL](https://img.shields.io/twitter/url?logoColor=blue&style=social&url=https%3A%2F%2Fimg.shields.io%2Ftwitter%2Furl%3Fstyle%3Dsocial)](https://twitter.com/intent/tweet?text=Checkout%20this%20%github%20repo%20by%20%40joaofse%20%F0%9F%91%A8%F0%9F%8F%BD%E2%80%8D%F0%9F%92%BB%3A%20https%3A//github.com/jesperancinha/favourite-lyrics-app)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Favourite%20Lyrics%20App🎶&color=informational)](https://github.com/jesperancinha/favourite-lyrics-app)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

[![FLA Build and reporting](https://github.com/jesperancinha/favourite-lyrics-app/actions/workflows/favorit-lyrics-app.yml/badge.svg)](https://github.com/jesperancinha/favourite-lyrics-app/actions/workflows/favorit-lyrics-app.yml)
[![FLA e2e-video-series-app](https://github.com/jesperancinha/favourite-lyrics-app/actions/workflows/favorit-lyrics-app-e2e.yml/badge.svg)](https://github.com/jesperancinha/favourite-lyrics-app/actions/workflows/favorit-lyrics-app-e2e.yml)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/765b3ff66aab454b9e04c9465a688e5f)](https://www.codacy.com/gh/jesperancinha/favourite-lyrics-app/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/favourite-lyrics-app&amp;utm_campaign=Badge_Grade)

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/765b3ff66aab454b9e04c9465a688e5f)](https://www.codacy.com/gh/jesperancinha/favourite-lyrics-app/dashboard?utm_source=github.com&utm_medium=referral&utm_content=jesperancinha/favourite-lyrics-app&utm_campaign=Badge_Coverage)
[![Coverage Status](https://coveralls.io/repos/github/jesperancinha/favourite-lyrics-app/badge.svg?branch=master)](https://coveralls.io/github/jesperancinha/favourite-lyrics-app?branch=master)
[![codecov](https://codecov.io/gh/jesperancinha/favourite-lyrics-app/branch/master/graph/badge.svg?token=kQluBZ2gJK)](https://codecov.io/gh/jesperancinha/favourite-lyrics-app)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/favourite-lyrics-app.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/favourite-lyrics-app.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/favourite-lyrics-app.svg)](#)

---

## Technologies used

Please check the [TechStack.md](TechStack.md) file for details.

## Introduction

---

This project is an implementation of a Favourite lyrics app.	It contains an implementation to be used as an example of Hexagonal Architecture implemented in Java and Spring.

This is a part of a series of studies I am making about DDD(Domain driven architecture)

This project is also the official support project of my article on medium:

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png "Medium")](https://medium.com/swlh/hexagonal-architecture-a596390269fd)
[Hexagonal Architecture](https://medium.com/swlh/hexagonal-architecture-a596390269fd)

<div align="center">
      <a title="Hexagonal Architecture" href="https://medium.com/swlh/hexagonal-architecture-a596390269fd">
     <img 
          src="./docs/images/articles.favourite.lyrics.intro.png" 
          style="width:100%;">
      </a>
</div>

#### Stable releases

-   [1.0.0](https://github.com/jesperancinha/favourite-lyrics-app/tree/1.0.0) - [cfe174af1a698da2c685d63ee50db317f2da53d2](https://github.com/jesperancinha/favourite-lyrics-app/tree/1.0.0)
-   [2.0.0](https://github.com/jesperancinha/favourite-lyrics-app/tree/2.0.0) - [e2c3dfef5d84da62e4561a78f7c9bc3d3647e180](https://github.com/jesperancinha/favourite-lyrics-app/tree/2.0.0) - JDK17 / Java
-   [3.0.0](https://github.com/jesperancinha/favourite-lyrics-app/tree/3.0.0) - [8b63ca3d32b3896f698118c66cd96e5a37cb6285](https://github.com/jesperancinha/favourite-lyrics-app/tree/3.0.0) - JDK19 / Kotlin 1.8.10

---

## Project Layout

-   [favourite-lyrics-domain](./favourite-lyrics-domain) - Domain libraries. This is where the domain model and the interfaces for it are located - (Inside)
-   [favourite-lyrics-jpa](./favourite-lyrics-jpa) - JPA libraries. This is the implementation of the domain libraries (Inside)
-   [favourite-lyrics-core](./favourite-lyrics-core) - Core libraries. This is where the services are located (Inside-Outside)
-   [favourite-lyrics-rest](./favourite-lyrics-rest) - REST Controller libraries. This is where the rest controller is located (Outside)
-   [favourite-lyrics-starter](./favourite-lyrics-starter) - Spring boot starter for this project
-   [favourite-lyrics-test](./favourite-lyrics-test) - Live running test project. (Not in included in docker-compose)
-   [favourite-lyrics-gui](./favourite-lyrics-gui) - Front end application to better visualize and understand the project (Not associated with Hexagonal architecture directly, but it is part of the Outside)

---

## Settings

This application has been tested to run with Java 17. The release being used is 13.0.1.hs-adpt, which is also known as:

```bash
sdk install java 17-open
sdk use java java 17-open
```

You need to have this version installed.	I've achieved this by using [SDK-MAN](https://sdkman.io/)

Alternatively just run this script from the command line:

```shell
. ./sdk17.sh
```

You also need to run an LTS version of node JS.

I have made script for this which will automatically install [NVM](https://github.com/nvm-sh/nvm) (Node Version Manager) and [Node 16](https://nodejs.org/en/about/releases/):

```shell
. ./node16.sh
```

## How to run

```shell
make docker-clean-build-start
```

-   Endpoints

1.  [http://localhost:8081/lyrics](http://localhost:8081/lyrics) - All Lyrics
2.  [http://localhost:8081/lyrics/random](http://localhost:8081/lyrics/random)

#### Swagger UI

-   [Homepage Swagger UI](http://localhost:8080/swagger-ui/index.html)

## References

-   [Flyway Documentation/Migrations](https://flywaydb.org/documentation/concepts/migrations.html#naming)
-   [PlantB Code Highlighter](http://www.planetb.ca/syntax-highlight-word)
-   [Hexagonal Architecture by Alistair Cockburn](https://alistair.cockburn.us/hexagonal-architecture/)
-   [Postman](https://www.getpostman.com/)
-   [Serving Web Content Spring IO](https://spring.io/guides/gs/serving-web-content/)
-   [JUnit](https://junit.org/junit5/docs/current/user-guide/)
-   [Mockito](https://site.mockito.org/)
-   [AssertJ](https://joel-costigliola.github.io/assertj/)

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
