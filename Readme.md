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

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-100/JEOrgLogo-27.png "João Esperancinha Homepage")](http://joaofilipesabinoesperancinha.nl)
[![](https://img.shields.io/badge/youtube-%230077B5.svg?style=for-the-badge&logo=youtube&color=FF0000)](https://www.youtube.com/@joaoesperancinha)
[![](https://img.shields.io/badge/Medium-12100E?style=for-the-badge&logo=medium&logoColor=white)](https://medium.com/@jofisaes)
[![](https://img.shields.io/badge/Buy%20Me%20A%20Coffee-%230077B5.svg?style=for-the-badge&logo=buymeacoffee&color=yellow)](https://www.buymeacoffee.com/jesperancinha)
[![](https://img.shields.io/badge/Twitter-%230077B5.svg?style=for-the-badge&logo=twitter&color=white)](https://twitter.com/joaofse)
[![](https://img.shields.io/badge/Mastodon-%230077B5.svg?style=for-the-badge&logo=mastodon&color=afd7f7)](https://masto.ai/@jesperancinha)
[![](https://img.shields.io/badge/Sessionize-%230077B5.svg?style=for-the-badge&logo=sessionize&color=cffff6)](https://sessionize.com/joao-esperancinha)
[![](https://img.shields.io/badge/Instagram-%230077B5.svg?style=for-the-badge&logo=instagram&color=purple)](https://www.instagram.com/joaofisaes)
[![](https://img.shields.io/badge/Tumblr-%230077B5.svg?style=for-the-badge&logo=tumblr&color=192841)](https://jofisaes.tumblr.com)
[![](https://img.shields.io/badge/Spotify-1ED760?style=for-the-badge&logo=spotify&logoColor=white)](https://open.spotify.com/user/jlnozkcomrxgsaip7yvffpqqm)
[![](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/joaoesperancinha/)
[![](https://img.shields.io/badge/Xing-%230077B5.svg?style=for-the-badge&logo=xing&color=064e40)](https://www.xing.com/profile/Joao_Esperancinha/cv)
[![](https://img.shields.io/badge/YCombinator-%230077B5.svg?style=for-the-badge&logo=ycombinator&color=d0d9cd)](https://news.ycombinator.com/user?id=jesperancinha)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=social "GitHub")](https://github.com/jesperancinha)
[![](https://img.shields.io/badge/bitbucket-%230077B5.svg?style=for-the-badge&logo=bitbucket&color=blue)](https://bitbucket.org/jesperancinha)
[![](https://img.shields.io/badge/gitlab-%230077B5.svg?style=for-the-badge&logo=gitlab&color=orange)](https://gitlab.com/jesperancinha)
[![](https://img.shields.io/badge/Stack%20Overflow-%230077B5.svg?style=for-the-badge&logo=stackoverflow&color=5A5A5A)](https://stackoverflow.com/users/3702839/joao-esperancinha)
[![](https://img.shields.io/badge/Credly-%230077B5.svg?style=for-the-badge&logo=credly&color=4d2a00)](https://www.credly.com/users/joao-esperancinha)
[![](https://img.shields.io/badge/Coursera-%230077B5.svg?style=for-the-badge&logo=coursera&color=000080)](https://www.coursera.org/user/da3ff90299fa9297e283ee8e65364ffb)
[![](https://img.shields.io/badge/Docker-%230077B5.svg?style=for-the-badge&logo=docker&color=cyan)](https://hub.docker.com/u/jesperancinha)
[![](https://img.shields.io/badge/Reddit-%230077B5.svg?style=for-the-badge&logo=reddit&color=black)](https://www.reddit.com/user/jesperancinha/)
[![](https://img.shields.io/badge/Hackernoon-%230077B5.svg?style=for-the-badge&logo=hackernoon&color=0a5d00)](https://hackernoon.com/@jesperancinha)
[![](https://img.shields.io/badge/Code%20Project-%230077B5.svg?style=for-the-badge&logo=codeproject&color=063b00)](https://www.codeproject.com/Members/jesperancinha)
[![](https://img.shields.io/badge/Free%20Code%20Camp-%230077B5.svg?style=for-the-badge&logo=freecodecamp&color=0a5d00)](https://www.freecodecamp.org/jofisaes)
[![](https://img.shields.io/badge/Hackerrank-%230077B5.svg?style=for-the-badge&logo=hackerrank&color=1e2f97)](https://www.hackerrank.com/jofisaes)
[![](https://img.shields.io/badge/LeetCode-%230077B5.svg?style=for-the-badge&logo=leetcode&color=002647)](https://leetcode.com/jofisaes)
[![](https://img.shields.io/badge/Codewars-%230077B5.svg?style=for-the-badge&logo=codewars&color=722F37)](https://www.codewars.com/users/jesperancinha)
[![](https://img.shields.io/badge/CodePen-%230077B5.svg?style=for-the-badge&logo=codepen&color=black)](https://codepen.io/jesperancinha)
[![](https://img.shields.io/badge/HackerEarth-%230077B5.svg?style=for-the-badge&logo=hackerearth&color=00035b)](https://www.hackerearth.com/@jofisaes)
[![](https://img.shields.io/badge/Khan%20Academy-%230077B5.svg?style=for-the-badge&logo=khanacademy&color=00035b)](https://www.khanacademy.org/profile/jofisaes)
[![](https://img.shields.io/badge/Pinterest-%230077B5.svg?style=for-the-badge&logo=pinterest&color=FF0000)](https://nl.pinterest.com/jesperancinha)
[![](https://img.shields.io/badge/Quora-%230077B5.svg?style=for-the-badge&logo=quora&color=FF0000)](https://nl.quora.com/profile/Jo%C3%A3o-Esperancinha)
[![](https://img.shields.io/badge/Google%20Play-%230077B5.svg?style=for-the-badge&logo=googleplay&color=purple)](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)
| [Sonatype Search Repos](https://search.maven.org/search?q=org.jesperancinha)
| [Dev.TO](https://dev.to/jofisaes)
| [Codebyte](https://coderbyte.com/profile/jesperancinha)
| [InfoQ](https://www.infoq.com/profile/Joao-Esperancinha.2/)
| [VMware Spring Professional 2021](https://www.credly.com/badges/762fa7a4-9cf4-417d-bd29-7e072d74cdb7)
| [Oracle Certified Professional, Java SE 11 Programmer](https://www.credly.com/badges/87609d8e-27c5-45c9-9e42-60a5e9283280)
| [Oracle Certified Professional, JEE7 Developer](https://www.credly.com/badges/27a14e06-f591-4105-91ca-8c3215ef39a2)
| [IBM Cybersecurity Analyst Professional](https://www.credly.com/badges/ad1f4abe-3dfa-4a8c-b3c7-bae4669ad8ce)
| [Certified Advanced JavaScript Developer](https://cancanit.com/certified/1462/)
| [Certified Neo4j Professional](https://graphacademy.neo4j.com/certificates/c279afd7c3988bd727f8b3acb44b87f7504f940aac952495ff827dbfcac024fb.pdf)
| [Deep Learning](https://www.credly.com/badges/8d27e38c-869d-4815-8df3-13762c642d64)
| [![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=JEsperancinhaOrg&color=yellow&style=for-the-badge "jesperancinha.org dependencies")](https://github.com/JEsperancinhaOrg)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=red&style=for-the-badge "All badges")](https://joaofilipesabinoesperancinha.nl/badges)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=red&style=for-the-badge "Project statuses")](https://github.com/jesperancinha/project-signer/blob/master/project-signer-quality/Build.md)
