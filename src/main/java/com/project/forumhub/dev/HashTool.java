package com.project.forumhub.dev;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashTool {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));

        @Bean
        CommandLineRunner printHashOnce(PasswordEncoder encoder) {
            return args -> {
                String raw = "minhasenha123"; // 👈 coloque a senha em texto que você quer usar no login
                String hash = encoder.encode(raw);
                System.out.println("\n\n=== HASH GERADO (BCrypt) ===\n" + hash + "\n============================\n");
            };
        }
    }
}
