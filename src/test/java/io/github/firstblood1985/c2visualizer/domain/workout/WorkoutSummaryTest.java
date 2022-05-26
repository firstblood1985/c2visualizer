package io.github.firstblood1985.c2visualizer.domain.workout;

import io.github.firstblood1985.c2visualizer.TestCommon;
import io.github.firstblood1985.c2visualizer.domain.user.SiteUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * usage of this class: WorkoutSummaryTest
 * created by limin @ 2022/4/20
 */
@RunWith(JUnit4.class)
public class WorkoutSummaryTest {

    List<WorkoutSummary> workoutSummaries;

    @Before
    public void setUp() {
        workoutSummaries = TestCommon.generateWorkoutSummaries();
    }

    @Test
    public void testLocalDateTimeToString(){
        String time = "April 18, 2022 12:59:00";
        String pattern = "MMMM dd, yyyy HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.US);

        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        System.out.println(dateTime);
    }

    @Test
    public void testDurationToString(){

        String t1 = "1:00:05.8";

        String[] time = t1.split(":");

        String durationT1 = "PT"+time[0]+'H'+time[1]+'M'+time[2]+'S';
        System.out.println(durationT1);

        System.out.println(Duration.parse(durationT1));

    }

    @Test
    public void toDTO(){
        WorkoutSummaryDTO workoutSummaryDTO = new WorkoutSummaryDTO(workoutSummaries.get(0));

        System.out.println(workoutSummaryDTO);
    }

}
