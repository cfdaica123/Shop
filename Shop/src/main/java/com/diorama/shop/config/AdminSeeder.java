package com.diorama.shop.config;

import com.diorama.shop.model.Role;
import com.diorama.shop.model.User;
import com.diorama.shop.repository.RoleRepository;
import com.diorama.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Order(2)
public class AdminSeeder implements CommandLineRunner {

    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!userRepository.existsByUsername("admin")) {
            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseThrow(() -> new RuntimeException("ADMIN not found"));

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@example.com");
            admin.setFullName("Default Admin");
            admin.setPhone("0123456789");
            admin.setRoles(Collections.singleton(adminRole));

            userRepository.save(admin);
            System.out.println("🛠️ Admin account created: admin / admin123");
        } else {
            System.out.println("✅ Admin account already exists");
        }
    }
}
