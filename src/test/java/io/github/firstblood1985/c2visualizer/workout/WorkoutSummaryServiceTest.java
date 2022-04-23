package io.github.firstblood1985.c2visualizer.workout;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * usage of this class: WorkoutSummaryServiceTest
 * created by limin @ 2022/4/22
 */
@RunWith(SpringRunner.class)
public class WorkoutSummaryServiceTest {

    @Test
    public void testLocalDateTOLocalDateTime(){
        LocalDate localDate = LocalDate.of(2022,4,22);

        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(0,0));

        Assert.assertEquals(LocalDateTime.of(2022,4,22,0,0),localDateTime);
    }
}
