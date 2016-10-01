package com.example;

import static org.assertj.core.api.Assertions.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.dto.DemoDto;

@RunWith(SpringRunner.class)
@SpringBootTest
@JsonTest
public class DemoJsonTest {

    @Autowired
    private JacksonTester<DemoDto> demoDtoJackson;

    @Test
    public void demoJsonTest() throws IOException {
        String dtoJsonStr = "{\"id\": 2, \"content\": \"mockmock\"}";
        DemoDto dto = new DemoDto(2, "mockmock");

        assertThat(demoDtoJackson.write(dto)).isEqualToJson("demotest.json");
        assertThat(demoDtoJackson.write(dto)).hasJsonPathNumberValue("@.id");
        assertThat(demoDtoJackson.write(dto)).extractingJsonPathStringValue("@.content")
                .isEqualTo("mockmock");

        assertThat(demoDtoJackson.parse(dtoJsonStr)).isEqualTo(dto);
        assertThat(demoDtoJackson.parseObject(dtoJsonStr).getContent()).isEqualTo("mockmock");
    }

}
