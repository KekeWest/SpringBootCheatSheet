package com.example;

import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.component.DemoComponent;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    private DemoComponent demoComponent;

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tokyo"));
        try {
            ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
            DemoApplication app = ctx.getBean(DemoApplication.class);
            app.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(String... args) throws Exception {
        demoComponent.printStr("test");
    }
}
