package io.github.firstblood1985.c2visualizer.repository;

import io.github.firstblood1985.c2visualizer.dao.WorkoutDetailRepository;
import io.github.firstblood1985.c2visualizer.dao.WorkoutSummaryRepository;
import io.github.firstblood1985.c2visualizer.domain.user.C2User;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutDetail;
import io.github.firstblood1985.c2visualizer.domain.workout.enums.WorkOutEnum;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummary;
import io.github.firstblood1985.c2visualizer.domain.workout.enums.WorkoutTypeEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    WorkoutDetailRepository workoutDetailRepository;

    @Autowired
    DataSource dataSource;

    @Test
    public void testWorkoutSummaryRepositoryNotNull() {
        Assert.assertNotNull(workoutSummaryRepository);
    }

    @Test
    public void testWorkoutSummaryRepositoryQuery() {
        List<WorkoutSummary> workoutSummaries = workoutSummaryRepository.findByWorkOut(WorkOutEnum.ROWERG);

        Assert.assertEquals(10, workoutSummaries.size());

        Assert.assertEquals(WorkoutTypeEnum.JUST_ROW, workoutSummaries.get(0).getWorkoutType());
    }

    @Test
    public void testGetWorkoutSummaryByLogBookIdAndTime() {
        C2User limin = new C2User();
        limin.setUsername("firstblood1985");
        limin.setLogbookId("1043029");

        LocalDateTime start = LocalDateTime.of(2022,4,21,0,0);
        LocalDateTime end = LocalDateTime.of(2022,4,21,23,59);

        Assert.assertEquals(4,workoutSummaryRepository.countByLogbookIdAndWorkoutDateTimeBetween(limin.getLogbookId(),start,end));

        List<WorkoutSummary> workoutSummaries = workoutSummaryRepository.findByLogbookIdAndWorkoutDateTimeBetween(
                limin.getLogbookId(),
                start,
                end,
                Pageable.unpaged()
        );

        Assert.assertEquals(4,workoutSummaries.size());

    }

    @Test
    @Transactional
    public void testGetWorkoutDetails(){

        WorkoutSummary workoutSummary = workoutSummaryRepository.findByLogId("63324246");
        Assert.assertEquals(6,workoutSummary.getWorkoutDetails().size());

        Optional<WorkoutDetail> workoutDetail = workoutDetailRepository.findById(Long.valueOf(1));
        System.out.println(workoutDetail.get());

        workoutSummary = workoutSummaryRepository.findByLogId("63280806");
        Assert.assertEquals(0,workoutSummary.getWorkoutDetails().size());

    }

}

