package br.com.caprica.spring.devopsbuddy.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
	
	@RequestMapping("/teste")
	public String sayHello() {
		return "index";
	}
}
