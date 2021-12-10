# Favourite Lyrics App Roadmap

2021/12/11 - Setup virtual environment
- Docker compose
- GUI deployment
- Check via logs

2021/12/09 - Upgrade to Java 17 and Spring Boot parent 2.6.1
- Cleanup dependencies
- Upgrade versions
- Remove obsolete versions


---

1. Remove H2 and all its references. Use Test containers insted
2. H2 console is not needed anymore since PostgreSQL will be used as a container
