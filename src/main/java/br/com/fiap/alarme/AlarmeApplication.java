package br.com.fiap.alarme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackages = "br.com.fiap.alarme.model")
public class AlarmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlarmeApplication.class, args);
	}

}
