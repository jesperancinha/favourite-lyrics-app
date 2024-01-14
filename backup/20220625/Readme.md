# Favourite lyrics app

---

[![Generic badge](https://img.shields.io/static/v1.svg?label=GitLab&message=Favourite%20Lyrics%20App🎶&color=informational)](https://gitlab.com/jesperancinha/favourite-lyrics-app)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

[![Gitlab pipeline status (branch)](https://img.shields.io/gitlab/pipeline/jesperancinha/favourite-lyrics-app/master)](https://gitlab.com/jesperancinha/favourite-lyrics-app/pipelines)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/03e7df2eb23c4545af7f32d2c1870074)](https://www.codacy.com/gl/jesperancinha/favourite-lyrics-app/dashboard?utm_source=gitlab.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/favourite-lyrics-app&amp;utm_campaign=Badge_Grade)

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/03e7df2eb23c4545af7f32d2c1870074)](https://www.codacy.com/gl/jesperancinha/favourite-lyrics-app/dashboard?utm_source=gitlab.com&utm_medium=referral&utm_content=jesperancinha/favourite-lyrics-app&utm_campaign=Badge_Coverage)
[![Coverage Status](https://coveralls.io/repos/gitlab/jesperancinha/favourite-lyrics-app/badge.svg?branch=master)](https://coveralls.io/gitlab/jesperancinha/favourite-lyrics-app?branch=master)
[![codecov](https://codecov.io/gl/jesperancinha/favourite-lyrics-app/branch/master/graph/badge.svg?token=7IA2J24R6H)](https://codecov.io/gl/jesperancinha/favourite-lyrics-app)

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

## Run Codecov

```bash
mvn clean test -Dconsolerizer.show=false
bash <(curl -s https://codecov.io/bash)
```

> NOTE: In GitLab pipelines, only protected branches and tags can access protected environment variables.

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
