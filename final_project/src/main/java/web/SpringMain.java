package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("web")
public class SpringMain { //extends SpringBootServletInitializer {

//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(SpringMain.class);
//	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(SpringMain.class, args);
	}

}
