package br.com.caprica.spring.devopsbuddy.web.i18n;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class I18NService {
	
	/** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(I18NService.class);
	
	@Autowired
	private MessageSource messageSource;
	
	public String getMessageSource(String messageId) {
		LOG.info("Retornando o texto i18n para o id: "+messageId);
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(messageId, null, locale);
	}
	
	public String getMessageSource(String messageId, Locale locale) {
		return messageSource.getMessage(messageId, null, locale);
	}
	
}
