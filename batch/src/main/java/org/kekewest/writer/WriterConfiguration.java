package org.kekewest.writer;

import javax.persistence.EntityManagerFactory;

import org.kekewest.entity.SampleTable;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WriterConfiguration {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public ItemWriter<SampleTable> sampleTableWriter() {
        JpaItemWriter<SampleTable> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

}
