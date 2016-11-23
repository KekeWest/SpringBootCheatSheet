package com.example;

import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.domain.dto.DemoDto;
import com.example.service.DemoService;
import com.example.service.HogeService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class JMockitTest {

    @Tested
    private DemoService demoService;

    @Injectable
    private HogeService hogeService;

    @Mocked
    private DemoDto demoDto;

    @Test
    public void demoServiceTest() {
        new Expectations() {
            {
                hogeService.getHogeStr();
                result = "test";
                demoDto.getId();
                result = 123L;
            }
        };

        String actual = demoService.getHogeStr();
        Assert.assertThat(actual, is(equalTo("test")));

        Assert.assertThat(demoDto.getId(), is(equalTo(123L)));
        Assert.assertThat(demoService.getDemoDto1().getId(), is(equalTo(123L)));

        new Verifications() {
            {
                hogeService.getHogeStr();
            }
        };
    }

}
