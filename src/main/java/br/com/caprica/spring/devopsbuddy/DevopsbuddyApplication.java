package br.com.caprica.spring.devopsbuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "br.com.caprica.spring.devopsbuddy.backend.persistence.repositories")
@SpringBootApplication
public class DevopsbuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevopsbuddyApplication.class, args);
	}
}
