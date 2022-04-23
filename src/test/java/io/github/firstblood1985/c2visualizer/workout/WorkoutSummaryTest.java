package io.github.firstblood1985.c2visualizer.workout;

import org.apache.tomcat.jni.Local;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * usage of this class: WorkoutSummaryTest
 * created by limin @ 2022/4/20
 */
@RunWith(SpringRunner.class)
public class WorkoutSummaryTest {

    @Test
    public void testLocalDateTimeToString(){
        String time = "April 18, 2022 12:59:00";
        String pattern = "MMMM dd, yyyy HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.US);

        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        System.out.println(dateTime);
    }

    @Test
    public void testLocalTimeToString(){
        final DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("H:mm:ss.S");
        final DateTimeFormatter formatterMinute = DateTimeFormatter.ofPattern("m:ss.S");

        String t1 = "1:00:05.8";

        LocalTime time1 = LocalTime.parse(t1,formatterHour);
        System.out.println(t1);

        String t1f = formatterMinute.format(time1);
        System.out.println(t1f);

        Assert.assertTrue(time1.isAfter(LocalTime.of(1,0)));

    }

}
