package com.diorama.shop.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.diorama.shop.model.Role;
import com.diorama.shop.model.Enums.RoleType;
import com.diorama.shop.repository.RoleRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        for (RoleType roleType : RoleType.values()) {
            if (roleRepository.findByName(roleType.name()).isEmpty()) {
                Role role = new Role();
                role.setName(roleType.name()); // nếu name là String
                roleRepository.save(role);
            }
        }
    }
}

