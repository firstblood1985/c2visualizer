package io.github.firstblood1985.c2visualizer.domain.workout;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * usage of this class: C2SeasonTest
 * created by limin @ 2022/5/7
 */
@RunWith(JUnit4.class)
public class C2SeasonTest {

    @Test
    public void testPeriods()
    {
        C2Season season =  C2Season.SEASON2022;
        long days = ChronoUnit.DAYS.between(season.seasonStart(),season.seasonEnd());
        Assert.assertEquals(364,days);
    }


}
