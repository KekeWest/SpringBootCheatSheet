package org.kekewest.job.splitsample;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
public class SplitJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private Tasklet1 tasklet1;

    @Autowired
    private Tasklet2 tasklet2;

    @Bean
    public Job splitJob1(Step tasklet1Step, Step tasklet2Step) {
        Flow flow1 = (new FlowBuilder<Flow>("flow1")).start(tasklet1Step).build();
        Flow flow2 = (new FlowBuilder<Flow>("flow2")).start(tasklet2Step).build();
        Flow splitFlow = (new FlowBuilder<Flow>("splitFlow"))
                .split(new SimpleAsyncTaskExecutor()).add(flow1, flow2).build();

        return jobBuilderFactory.get("splitJob1")
                .incrementer(new RunIdIncrementer())
                .start(splitFlow)
                .end()
                .build();
    }

    @Bean
    public Step tasklet1Step() {
        return stepBuilderFactory
                .get("tasklet1")
                .tasklet(tasklet1)
                .build();
    }

    @Bean
    public Step tasklet2Step() {
        return stepBuilderFactory
                .get("tasklet2")
                .tasklet(tasklet2)
                .build();
    }

}
