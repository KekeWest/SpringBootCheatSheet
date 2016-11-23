package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.dto.DemoDto;

@Service
public class DemoService {

    @Autowired
    private HogeService hogeService;

    private DemoDto dto = new DemoDto(2, "demo json");

    public String getHogeStr() {
        return hogeService.getHogeStr();
    }

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
