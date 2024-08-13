package com.example.trade_site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TradeSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeSiteApplication.class, args);
	}

}
