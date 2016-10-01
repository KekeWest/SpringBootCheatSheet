package com.example;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.dto.DemoDto;
import com.example.service.DemoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoMockTest {

    @MockBean
    private DemoService demoServiveMock;

    @Test
    public void demoMockTest() {
        given(demoServiveMock.getDemoDto1()).willReturn(new DemoDto(1, "mock"));
        DemoDto dto = demoServiveMock.getDemoDto1();

        assertThat(dto.getId()).isEqualTo(1);
        assertThat(dto.getContent()).isEqualTo("mock");
    }

}
