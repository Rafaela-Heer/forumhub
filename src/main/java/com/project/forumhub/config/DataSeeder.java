package com.project.forumhub.config;

import com.project.forumhub.domain.User;
import com.project.forumhub.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initUsers(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByEmail("admin@forumhub.com").isEmpty()) {
                User u = new User();
                u.setName("Admin");
                u.setEmail("admin@forumhub.com");
                u.setPassword(encoder.encode("123456"));
                u.setRole(User.Role.ADMIN);
                repo.save(u);
            }
        };
    }
}
