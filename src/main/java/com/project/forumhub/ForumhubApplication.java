package com.project.forumhub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class ForumhubApplication {

	public static void main(String[] args) {
		org.springframework.boot.SpringApplication.run(ForumhubApplication.class, args);
	}


	@Bean
	CommandLineRunner printHashOnce() {
		return args -> {
			var encoder = new BCryptPasswordEncoder();
			String raw = "minhasenha123";
			System.out.println(encoder.encode(raw));
		};
	}

}
