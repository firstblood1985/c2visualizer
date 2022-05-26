package io.github.firstblood1985.c2visualizer.domain.workout;

import com.google.gson.Gson;
import io.github.firstblood1985.c2visualizer.TestCommon;
import io.github.firstblood1985.c2visualizer.domain.user.SiteUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDate;
import java.util.List;

/**
 * usage of this class: WorkoutStatsTest
 * created by limin @ 2022/5/7
 */
@RunWith(JUnit4.class)
public  class WorkoutStatsTest {
    List<WorkoutSummary> workoutSummaries;

    SiteUser siteUser;

    LocalDate start;

    LocalDate end;

    @Before
    public void setUp() throws Exception {
        workoutSummaries = TestCommon.generateWorkoutSummaries();
        siteUser = TestCommon.generateSiteUser();
        start = LocalDate.of(2022,4,21);
        end = LocalDate.of(2022,4,23);

    }

    @Test
    public void testNewWorkoutStats() {
        WorkoutStats workoutStats = new WorkoutStats(workoutSummaries,start,end);

        WorkoutStatsDTO workoutStatsDTO = new WorkoutStatsDTO(workoutStats);

        Assert.assertEquals(workoutStatsDTO.getStart(),workoutStats.getStart());

        System.out.println(new Gson().toJson(workoutStatsDTO));
    }

    @Test
    public void testSeasonWorkoutStats(){
        C2Season season = C2Season.SEASON2022;

        WorkoutStats workoutStats = new WorkoutStats(workoutSummaries,season.seasonStart(),season.seasonEnd());

        Long numberOfDays = workoutStats.numberOfDays();

        Assert.assertEquals(364,numberOfDays.longValue());

        Assert.assertEquals(4,workoutStats.numberOfWorkouts().longValue());
    }



}