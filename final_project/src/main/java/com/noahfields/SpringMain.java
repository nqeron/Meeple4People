package com.noahfields;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.noahfields")
public class SpringMain extends SpringBootServletInitializer { //extends SpringBootServletInitializer {

//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(SpringMain.class);
//	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	      return builder.sources(SpringMain.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringMain.class, args);
	}

}
