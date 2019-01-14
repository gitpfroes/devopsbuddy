package br.com.caprica.spring.devopsbuddy.backend.service;

import org.springframework.mail.SimpleMailMessage;

import br.com.caprica.spring.devopsbuddy.web.domain.frontend.FeedbackPojo;

/**
 * Contract for Email Service
 * */
public interface EmailService {
	public void sendFeedbackEmail(FeedbackPojo feedback);
    public void sendGenericEmailMessage(SimpleMailMessage message);
}
