package com.xebia.irrigation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories("com.xebia.irrigation.*")
@EntityScan("com.xebia.irrigation.*")
@Configuration
@EnableAsync
@EnableScheduling
@EnableRetry
public class IrrigationApplication {

	public static void main(String[] args) {
		SpringApplication.run(IrrigationApplication.class, args);
	}

}
