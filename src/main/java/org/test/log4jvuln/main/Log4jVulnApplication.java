package org.test.log4jvuln.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@ComponentScan(basePackages = {"org.test"})
public class Log4jVulnApplication {
	public static void main(String[] args) {
		SpringApplication.run(Log4jVulnApplication.class, args);
	}
}
