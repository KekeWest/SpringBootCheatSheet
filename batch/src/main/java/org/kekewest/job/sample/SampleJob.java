package org.kekewest.job.sample;

import org.kekewest.BatchProperties;
import org.kekewest.entity.SampleTable;
import org.kekewest.entity.TempSampleTable;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class SampleJob {

    @Autowired
    private BatchProperties batchProperties;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private InsertDataTasklet insertDataTasklet;

    @Autowired
    private SampleDataProcessor sampleDataProcessor;

    @Bean
    public Job sampleJob1(Step insertStep, Step dataStep) {
        return jobBuilderFactory.get("sampleJob1")
                .incrementer(new RunIdIncrementer())
                .flow(insertStep).on(ExitStatus.COMPLETED.getExitCode()).to(dataStep)
                .from(insertStep).on(ExitStatus.FAILED.getExitCode()).fail()
                .from(dataStep).on(ExitStatus.COMPLETED.getExitCode()).end()
                .from(dataStep).on("*").fail()
                .end()
                .build();
    }

    @Bean
    public Step insertStep() {
        return stepBuilderFactory
                .get("insertStep")
                .tasklet(insertDataTasklet)
                .build();
    }

    @Bean
    public Step dataStep(
            ItemReader<TempSampleTable> tempSampleTableReader,
            ItemWriter<SampleTable> sampleTableWriter) {
        return stepBuilderFactory
                .get("dataStep")
                .<TempSampleTable, SampleTable> chunk(batchProperties.getCommitInterval())
                .reader(tempSampleTableReader)
                .processor(sampleDataProcessor)
                .writer(sampleTableWriter)
                .build();
    }

}
