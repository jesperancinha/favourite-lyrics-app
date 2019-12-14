package org.jesperancinha.lyrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LyricsDemoApplicationLauncher {
    public static void main(String[] args) {
        SpringApplication.run(LyricsDemoApplicationLauncher.class);
    }
}
