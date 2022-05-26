package io.github.firstblood1985.c2visualizer.domain.analysis;

import io.github.firstblood1985.c2visualizer.common.util.Utils;
import io.github.firstblood1985.c2visualizer.domain.analysis.filter.DurationFilter;
import io.github.firstblood1985.c2visualizer.domain.analysis.filter.PaceFilter;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * usage of this class: AnalysisFilterTest
 * created by limin @ 2022/5/15
 */
@RunWith(JUnit4.class)
public class AnalysisFilterTest {

    WorkoutSummary workoutSummary;

    @Before
    public void setUp() {
        workoutSummary = new WorkoutSummary();
        workoutSummary.setPace("1:52.8");
        workoutSummary.setDuration(Utils.StringToDuration("35:00"));
    }

    @Test
    public void stringToDurationTest()
    {
        String s = "1:00:00.0";
        System.out.println(Utils.StringToDuration(s));
    }

    @Test
    public void testIntegerToString(){
        String a = "01";
        Assert.assertEquals(1,Integer.valueOf(a).intValue());
    }

    @Test
    public void testPaceAnalysisFilter() {
        PaceFilter filter = new PaceFilter("BETWEEN 1:53 1:52");

        Assert.assertTrue(filter.filter(workoutSummary));

        PaceFilter filter1 = new PaceFilter("GET 1:53");
        Assert.assertTrue(filter1.filter(workoutSummary));
        PaceFilter filter2 = new PaceFilter("GET 1:51");
        Assert.assertFalse(filter2.filter(workoutSummary));

        PaceFilter filter3 = new PaceFilter("LET 1:52");
        Assert.assertTrue(filter3.filter(workoutSummary));

        PaceFilter filter4 = new PaceFilter("LET 1:53");
        Assert.assertFalse(filter4.filter(workoutSummary));

    }

    @Test
    public void testDurationAnalysisFilter() {
        DurationFilter filter = new DurationFilter("BETWEEN 30 40");
        Assert.assertTrue(filter.filter(workoutSummary));

        filter = new DurationFilter("LET 40");
        Assert.assertTrue(filter.filter(workoutSummary));
        filter = new DurationFilter("LET 30");
        Assert.assertFalse(filter.filter(workoutSummary));

    }
}
