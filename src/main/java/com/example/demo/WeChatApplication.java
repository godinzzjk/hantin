package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.ws.MyWebSocketHandler;

@SpringBootApplication
public class WeChatApplication {

	public static void main(String[] args) {
//		SpringApplication.run(WeChatApplication.class, args);
		SpringApplication springApplication = new SpringApplication(WeChatApplication.class);        
		ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);        
		MyWebSocketHandler.ac=configurableApplicationContext;//非常重要
		
		
		
	}

}

