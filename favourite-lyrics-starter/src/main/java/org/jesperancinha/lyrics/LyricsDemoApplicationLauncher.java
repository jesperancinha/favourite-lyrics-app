package org.jesperancinha.lyrics;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.ForwardedHeaderFilter;

@SpringBootApplication
@EnableTransactionManagement
@OpenAPIDefinition(
        info = @Info(
                title = "OpenAPI definition"
        ),
        servers = {
                @Server(url = "${fla.server.url}", description = "Server URL")
        }
)
public class LyricsDemoApplicationLauncher {
    public static void main(String[] args) {
        SpringApplication.run(LyricsDemoApplicationLauncher.class);
    }
}

