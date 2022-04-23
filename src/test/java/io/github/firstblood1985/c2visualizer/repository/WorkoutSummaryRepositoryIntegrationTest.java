package io.github.firstblood1985.c2visualizer.repository;

import io.github.firstblood1985.c2visualizer.dao.WorkoutSummaryRepository;
import io.github.firstblood1985.c2visualizer.domain.workout.enums.WorkOutEnum;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummary;
import io.github.firstblood1985.c2visualizer.domain.workout.enums.WorkoutTypeEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

/**
 * usage of this class: WorkoutSummaryRepositoryTest
 * created by limin @ 2022/4/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkoutSummaryRepositoryIntegrationTest {
    @Autowired
    WorkoutSummaryRepository workoutSummaryRepository;

    @Autowired
    DataSource dataSource;

    @Test
    public void testWorkoutSummaryRepositoryNotNull() {
        Assert.assertNotNull(workoutSummaryRepository);
    }

    @Test
    public void testWorkoutSummaryRepositoryQuery(){
//        List<WorkoutSummary> workoutSummaries = workoutSummaryRepository.findByWorkOut(WorkOutEnum.ROWERG);
//
//        Assert.assertEquals(1,workoutSummaries.size());
//
//        Assert.assertEquals(WorkoutTypeEnum.FIXED_DISTANCE,workoutSummaries.get(0).getWorkoutType());
    }
}

