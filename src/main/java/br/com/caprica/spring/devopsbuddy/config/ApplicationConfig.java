package br.com.caprica.spring.devopsbuddy.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.caprica.spring.devopsbuddy.backend.persistence.repositories")
@EntityScan(basePackages = "br.com.caprica.spring.devopsbuddy.backend")
@EnableTransactionManagement
public class ApplicationConfig {
}
