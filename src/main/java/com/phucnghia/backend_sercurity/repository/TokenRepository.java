package com.phucnghia.backend_sercurity.repository;

import com.phucnghia.backend_sercurity.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByUserName(String username);
}
