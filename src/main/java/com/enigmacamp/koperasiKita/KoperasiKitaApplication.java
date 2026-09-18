package com.enigmacamp.koperasiKita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KoperasiKitaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KoperasiKitaApplication.class, args);
	}

}
