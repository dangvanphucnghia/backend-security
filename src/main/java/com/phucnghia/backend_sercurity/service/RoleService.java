package com.phucnghia.backend_sercurity.service;

import com.phucnghia.backend_sercurity.model.Role;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record RoleService(RoleRepository repository) {
    @PostConstruct
    public List<Role> findAll(){
        List<Role> roles = repository.findAll();

        return roles;
    }
}
