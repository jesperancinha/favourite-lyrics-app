
package org.jesperancinha.lyrics

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
@OpenAPIDefinition(
    info = Info(title = "OpenAPI definition"),
    servers = [Server(url = "\${fla.server.url}", description = "Server URL")]
)
class LyricsDemoApplicationLauncher

fun main(args: Array<String>) {
    SpringApplication.run(LyricsDemoApplicationLauncher::class.java, *args)
}