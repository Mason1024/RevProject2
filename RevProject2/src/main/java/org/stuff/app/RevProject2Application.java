package org.stuff.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("org.stuff")
@EntityScan("org.stuff.entities")
@EnableJpaRepositories("org.stuff.repo")
public class RevProject2Application {

	public static void main(String[] args) {
		SpringApplication.run(RevProject2Application.class, args);
	}

}
