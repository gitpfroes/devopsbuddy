package br.com.caprica.spring.devopsbuddy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import br.com.caprica.spring.devopsbuddy.backend.service.EmailService;
import br.com.caprica.spring.devopsbuddy.backend.service.SmtpEmailService;

@Configuration
@Profile("prod")
@PropertySource("file:///D:\\Controle\\application-dev.properties")
public class ProductionConfig {
	@Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
