package com.project.forumhub.dev;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class HashTool implements CommandLineRunner {

    private final PasswordEncoder encoder;

    public HashTool(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        String raw = "minhasenha123";
        String hash = encoder.encode(raw);
        System.out.println("\n HASH GERADO (BCrypt): \n" + hash);
    }
}
