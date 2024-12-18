package com.todolist.bff_todolist;

import com.todolist.bff_todolist.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class BffTodolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffTodolistApplication.class, args);
	}

}
