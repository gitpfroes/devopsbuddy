package br.com.caprica.spring.devopsbuddy.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.caprica.spring.devopsbuddy.web.domain.frontend.FeedbackPojo;

public abstract class AbstractEmailService implements EmailService {
	
    @Value("${default.to.address}")
	private String defaultToAddress;
    
    protected SimpleMailMessage prepareSimpleMailMessageFromFeedbackPojo(FeedbackPojo feedback) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(defaultToAddress);
        message.setFrom(feedback.getEmail());
        message.setReplyTo(feedback.getEmail());
        message.setSubject("[DevOps Buddy]: Feedback received from " + feedback.getFirstName() + " " + feedback
                .getLastName() + "!");
        message.setText("User with email: " + feedback.getEmail() + " left this feedback:\n" + feedback.getFeedback());
        return message;
    }
    
    @Override
    public void sendFeedbackEmail(FeedbackPojo feedback) {
    	sendGenericEmailMessage(prepareSimpleMailMessageFromFeedbackPojo(feedback));
    }
	
}
