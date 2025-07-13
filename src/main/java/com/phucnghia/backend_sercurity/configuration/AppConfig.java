package com.phucnghia.backend_sercurity.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!prod")
@RequiredArgsConstructor
public class AppConfig {
    private final UserService userService;
}
