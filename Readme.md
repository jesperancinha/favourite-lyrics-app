# Favourite lyrics app

---

[![Generic badge](https://img.shields.io/static/v1.svg?label=GitLab&message=Favourite%20Lyrics%20App🎶&color=informational)](https://gitlab.com/jesperancinha/favourite-lyrics-app)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)


[![Codacy Badge](https://app.codacy.com/project/badge/Grade/03e7df2eb23c4545af7f32d2c1870074)](https://www.codacy.com/gl/jesperancinha/favourite-lyrics-app/dashboard?utm_source=gitlab.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/favourite-lyrics-app&amp;utm_campaign=Badge_Grade)

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/03e7df2eb23c4545af7f32d2c1870074)](https://www.codacy.com/gl/jesperancinha/favourite-lyrics-app/dashboard?utm_source=gitlab.com&utm_medium=referral&utm_content=jesperancinha/favourite-lyrics-app&utm_campaign=Badge_Coverage)
[![Coverage Status](https://coveralls.io/repos/gitlab/jesperancinha/favourite-lyrics-app/badge.svg?branch=master)](https://coveralls.io/gitlab/jesperancinha/favourite-lyrics-app?branch=master)
[![codecov](https://codecov.io/gl/jesperancinha/favourite-lyrics-app/branch/master/graph/badge.svg?token=7IA2J24R6H)](https://codecov.io/gl/jesperancinha/favourite-lyrics-app)

---

## Technologies used

---

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/java-50.png "Java")](https://www.oracle.com/nl/java/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/lombok-50.png "Lombok")](https://projectlombok.org/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-50.png "Spring Framework")](https://spring.io/projects/spring-framework)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/spring-boot-50.png "Spring Boot")](https://spring.io/projects/spring-boot)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/mockito-50.png "Mockito")](https://site.mockito.org/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/assertj-50.png "AssertJ")](https://assertj.github.io/doc/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/h2-50.png "H2")](https://www.h2database.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/swagger-50.png "Swagger")](https://swagger.io/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-50/cypress-50.png "Cypress")](https://www.cypress.io/)

---

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
-   [2.0.0](https://github.com/jesperancinha/favourite-lyrics-app/tree/2.0.0) - [e2c3dfef5d84da62e4561a78f7c9bc3d3647e180](https://github.com/jesperancinha/favourite-lyrics-app/tree/2.0.0)

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

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/JEOrgLogo-20.png "João Esperancinha Homepage")](http://joaofilipesabinoesperancinha.nl)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=social "GitHub")](https://github.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/mastodon-20.png "Mastodon")](https://masto.ai/@jesperancinha)
[![Twitter Follow](https://img.shields.io/twitter/follow/joaofse?label=João%20Esperancinha&style=social "Twitter")](https://twitter.com/joaofse)
| [Sessionize](https://sessionize.com/joao-esperancinha/)
| [Spotify](https://open.spotify.com/user/jlnozkcomrxgsaip7yvffpqqm?si=b54b89eae8894960)
| [Medium](https://medium.com/@jofisaes)
| [YouTube](https://www.youtube.com/@joaoesperancinha/featured)
| [Instagram](https://www.instagram.com/joaofisaes/)
| [Buy me a coffee](https://www.buymeacoffee.com/jesperancinha)
| [Credly Badges](https://www.credly.com/users/joao-esperancinha)
| [Google Apps](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)
| [Sonatype Search Repos](https://search.maven.org/search?q=org.jesperancinha)
| [Docker Images](https://hub.docker.com/u/jesperancinha)
| [Stack Overflow Profile](https://stackoverflow.com/users/3702839/joao-esperancinha)
| [Reddit](https://www.reddit.com/user/jesperancinha/)
| [Dev.TO](https://dev.to/jofisaes)
| [Hackernoon](https://hackernoon.com/@jesperancinha)
| [Code Project](https://www.codeproject.com/Members/jesperancinha)
| [BitBucket](https://bitbucket.org/jesperancinha)
| [GitLab](https://gitlab.com/jesperancinha)
| [Coursera](https://www.coursera.org/user/da3ff90299fa9297e283ee8e65364ffb)
| [FreeCodeCamp](https://www.freecodecamp.org/jofisaes)
| [HackerRank](https://www.hackerrank.com/jofisaes)
| [LeetCode](https://leetcode.com/jofisaes)
| [Codebyte](https://coderbyte.com/profile/jesperancinha)
| [CodeWars](https://www.codewars.com/users/jesperancinha)
| [Code Pen](https://codepen.io/jesperancinha)
| [Hacker Earth](https://www.hackerearth.com/@jofisaes)
| [Khan Academy](https://www.khanacademy.org/profile/jofisaes)
| [Hacker News](https://news.ycombinator.com/user?id=jesperancinha)
| [InfoQ](https://www.infoq.com/profile/Joao-Esperancinha.2/)
| [LinkedIn](https://www.linkedin.com/in/joaoesperancinha/)
| [Xing](https://www.xing.com/profile/Joao_Esperancinha/cv)
| [Tumblr](https://jofisaes.tumblr.com/)
| [Pinterest](https://nl.pinterest.com/jesperancinha/)
| [Quora](https://nl.quora.com/profile/Jo%C3%A3o-Esperancinha)
| [VMware Spring Professional 2021](https://www.credly.com/badges/762fa7a4-9cf4-417d-bd29-7e072d74cdb7)
| [Oracle Certified Professional, Java SE 11 Programmer](https://www.credly.com/badges/87609d8e-27c5-45c9-9e42-60a5e9283280)
| [Oracle Certified Professional, JEE7 Developer](https://www.credly.com/badges/27a14e06-f591-4105-91ca-8c3215ef39a2)
| [IBM Cybersecurity Analyst Professional](https://www.credly.com/badges/ad1f4abe-3dfa-4a8c-b3c7-bae4669ad8ce)
| [Certified Advanced JavaScript Developer](https://cancanit.com/certified/1462/)
| [Certified Neo4j Professional](https://graphacademy.neo4j.com/certificates/c279afd7c3988bd727f8b3acb44b87f7504f940aac952495ff827dbfcac024fb.pdf)
| [Deep Learning](https://www.credly.com/badges/8d27e38c-869d-4815-8df3-13762c642d64)
| [![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=JEsperancinhaOrg&color=yellow "jesperancinha.org dependencies")](https://github.com/JEsperancinhaOrg)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=red "All badges")](https://joaofilipesabinoesperancinha.nl/badges)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=red "Project statuses")](https://github.com/jesperancinha/project-signer/blob/master/project-signer-quality/Build.md)
