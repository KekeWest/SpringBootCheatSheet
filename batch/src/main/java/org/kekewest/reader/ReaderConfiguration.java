package org.kekewest.reader;

import javax.persistence.EntityManagerFactory;

import org.kekewest.BatchProperties;
import org.kekewest.entity.TempSampleTable;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReaderConfiguration {

    @Autowired
    private BatchProperties batchProperties;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean(destroyMethod = "") // WARNING: org.springframework.batch.item.ItemStreamException: Error while closing item reader
    public ItemReader<TempSampleTable> tempSampleTableReader() {
        JpaPagingItemReader<TempSampleTable> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setQueryString("select s from " + TempSampleTable.class.getSimpleName() + " s");
        reader.setPageSize(batchProperties.getReadPageSize());
        return reader;
    }

}
