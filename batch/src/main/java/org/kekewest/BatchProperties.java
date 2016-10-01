package org.kekewest;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@ToString
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "batch")
public class BatchProperties {

    static protected final Integer DEFAULT_READPAGESIZE = 100;
    static protected final Integer DEFAULT_COMMITINTERVAL = 100;

    private Integer readPageSize = DEFAULT_READPAGESIZE;

    private Integer commitInterval = DEFAULT_COMMITINTERVAL;

    @PostConstruct
    public void showBatchProperties() {
        log.info(this.toString());
    }

}
