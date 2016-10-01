package org.kekewest.job.sample;

import org.kekewest.entity.SampleTable;
import org.kekewest.entity.TempSampleTable;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class SampleDataProcessor implements ItemProcessor<TempSampleTable, SampleTable> {

    @Override
    public SampleTable process(final TempSampleTable tempSampleTable) throws Exception {
        SampleTable sampleTable = new SampleTable();
        org.springframework.beans.BeanUtils.copyProperties(tempSampleTable, sampleTable);
        System.out.println(sampleTable.toString());
        return sampleTable;
    }

}