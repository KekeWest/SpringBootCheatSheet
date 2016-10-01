package org.kekewest.job.sample;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.kekewest.entity.TempSampleTable;
import org.kekewest.repository.TempSampleTableRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsertDataTasklet implements Tasklet {

    @Autowired
    TempSampleTableRepository tempSampleTableRepository;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        insertData();
        showData();
        return RepeatStatus.FINISHED;
    }

    protected void insertData() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        try {
            for (int i = 0; i < 10; i++) {
                entityManager
                        .createNativeQuery(
                                "insert into temp_sample_table (staff_code, name) values (:staffCode, :name)")
                        .setParameter("staffCode", String.valueOf(i))
                        .setParameter("name", "name" + String.valueOf(i))
                        .executeUpdate();
            }
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @SuppressWarnings("unchecked")
    protected void showData() {
        List<TempSampleTable> result = Collections.emptyList();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        try {
            result = entityManager
                    .createNativeQuery(
                            "select * from temp_sample_table", TempSampleTable.class)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

        for (TempSampleTable table : result) {
            System.out.println(table.toString());
        }
    }

}
