package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

@Configuration
@EnableSpringHttpSession
public class SessionConfig {

    @Value("${server.session.timeout}")
    private Integer sessionTimeout;

    @Bean
    public SessionRepository<ExpiringSession> sessionRepository() {
        CustomMapSessionRepository repository = new CustomMapSessionRepository();
        if (sessionTimeout != null) {
            repository.setDefaultMaxInactiveInterval(sessionTimeout);
        }
        return repository;
    }

}
