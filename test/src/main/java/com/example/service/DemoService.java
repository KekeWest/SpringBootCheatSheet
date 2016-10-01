package com.example.service;

import org.springframework.stereotype.Service;

import com.example.domain.dto.DemoDto;

@Service
public class DemoService {

    private DemoDto dto = new DemoDto(2, "demo json");

    public DemoDto getDemoDto1() {
        return createDemoDto();
    }

    public DemoDto getDemoDto2() {
        return createDemoDto();
    }

    private DemoDto createDemoDto() {
        return dto;
    }

}
