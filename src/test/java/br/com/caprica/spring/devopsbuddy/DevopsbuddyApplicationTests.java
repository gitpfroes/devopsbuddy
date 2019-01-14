package br.com.caprica.spring.devopsbuddy;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.caprica.spring.devopsbuddy.web.i18n.I18NService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DevopsbuddyApplicationTests {

	@Autowired
	private I18NService i18nService;
	
	@Test
	public void testMessageByLocaleService() {
		String expectedResult = "Hello, Spring Boot World!";
		String messageId = "index.main.callout";
		String actual = i18nService.getMessageSource(messageId);
		Assert.assertEquals("No match!", expectedResult, actual);
	}

}
