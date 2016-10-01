package com.example;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.dto.DemoDto;
import com.example.service.DemoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoPrivateFieldMockTest {

    @SpyBean
    private DemoService demoService;

    @Test
    public void demoPrivateFieldTest() {
        Whitebox.setInternalState(demoService, "dto", new DemoDto(3, "private test"));
        DemoDto dto = demoService.getDemoDto1();

        assertThat(dto.getId()).isEqualTo(3);
        assertThat(dto.getContent()).isEqualTo("private test");
    }

}
