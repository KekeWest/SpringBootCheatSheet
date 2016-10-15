package com.example;

import org.springframework.session.ExpiringSession;
import org.springframework.session.MapSessionRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomMapSessionRepository extends MapSessionRepository {

    @Override
    public void save(ExpiringSession session) {
        super.save(session);
        log.info("session has saved. (SessionID: {})", session.getId());
    }

    public void delete(String id) {
        super.delete(id);
        log.info("session has deleted. (SessionID: {})", id);
    }

}
