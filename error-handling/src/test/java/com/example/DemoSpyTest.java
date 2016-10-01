package com.example;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.dto.DemoDto;
import com.example.service.DemoService;

@SuppressWarnings("unused")
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSpyTest {

    @SpyBean
    private DemoService demoServiceSpy;

    @Test
    public void demoSpyTest() {
        given(demoServiceSpy.getDemoDto1()).willReturn(new DemoDto(1, "mock"));
        DemoDto dto1 = demoServiceSpy.getDemoDto1();
        DemoDto dto2 = demoServiceSpy.getDemoDto2();

        assertThat(dto1.getId()).isEqualTo(1);
        assertThat(dto1.getContent()).isEqualTo("mock");
        assertThat(dto2.getId()).isEqualTo(2);
        assertThat(dto2.getContent()).isEqualTo("demo json");
        verify(demoServiceSpy).getDemoDto1();
    }

}
