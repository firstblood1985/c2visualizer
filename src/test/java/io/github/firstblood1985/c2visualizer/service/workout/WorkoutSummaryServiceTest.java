package io.github.firstblood1985.c2visualizer.service.workout;

import io.github.firstblood1985.c2visualizer.TestCommon;
import io.github.firstblood1985.c2visualizer.common.util.Utils;
import io.github.firstblood1985.c2visualizer.dao.WorkoutSummaryRepository;
import io.github.firstblood1985.c2visualizer.domain.user.SiteUser;
import io.github.firstblood1985.c2visualizer.domain.workout.C2Season;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutStats;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummary;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummaryFilter;
import io.github.firstblood1985.c2visualizer.service.workout.impl.WorkoutSummaryServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.*;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * usage of this class: WorkoutSummaryServiceTest
 * created by limin @ 2022/4/22
 */
@RunWith(SpringRunner.class)
public class WorkoutSummaryServiceTest {

    @Mock
    WorkoutSummaryRepository workoutSummaryRepository;

    @InjectMocks
    WorkoutSummaryService workoutSummaryService = new WorkoutSummaryServiceImpl();

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

        when(workoutSummaryRepository.findByLogbookIdAndWorkoutDateTimeBetween(eq("1043029"),
                any(),any(),eq(Pageable.unpaged()))).thenReturn(workoutSummaries);
    }
    @Test
    public void testLocalDateTOLocalDateTime(){
        LocalDate localDate1 = LocalDate.of(2022,5,9);
        Assert.assertEquals("2022-05-09",localDate1.toString());

        LocalDate localDate = LocalDate.of(2022,4,22);

        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.of(0,0));

        Assert.assertEquals(LocalDateTime.of(2022,4,22,0,0),localDateTime);

        String date="2022-05-10";
        localDate1 = LocalDate.parse(date);

        Assert.assertEquals("2022-05-10",localDate1.toString());
    }


    @Test
    public void getWorkoutSummaryBySiteUser() {

        List<WorkoutSummary> myWorkSummaries = workoutSummaryService.getWorkoutSummaryBySiteUser(siteUser,start,end);
        Assert.assertEquals(4,workoutSummaries.size());
    }


    @Test
    public void getWorkoutCountPerDay() {


        Map<LocalDate,Long> countsPerDay = workoutSummaryService.getWorkoutCountPerDay(siteUser,start,end);
        Assert.assertEquals(3,countsPerDay.size());

        Assert.assertEquals(2,countsPerDay.get(LocalDate.of(2022,4,21)).longValue());
    }

    @Test
    public void getWorkoutCount() {
        when(workoutSummaryRepository.countByLogbookIdAndWorkoutDateTimeBetween(eq("1043029"),
                any(),any())).thenReturn(4L);
        long count = workoutSummaryService.getWorkoutCount(siteUser,start,end);
        Assert.assertEquals(4,count);
    }

    @Test
    public void getWorkoutSummaryByFilter() {
        WorkoutSummaryFilter filter = WorkoutSummaryFilter.builder().
                minMeters(10000).maxMeters(10000).
                start(start).end(start.plus(Period.ofDays(2)))
                .build();
        List<WorkoutSummary> workoutSummaries = workoutSummaryService.getWorkoutSummaryByFilter(siteUser,filter);
        Assert.assertEquals(2,workoutSummaries.size());

    }

    @Test
    public void getWorkoutMeters() {
       int workoutMeters= workoutSummaryService.getWorkoutMeters(siteUser,start,end);
       Assert.assertEquals(26500,workoutMeters);

    }

    @Test
    public void getWorkoutDurations() {
       Duration duration = workoutSummaryService.getWorkoutDurations(siteUser,start,end);
       Assert.assertEquals(Duration.ofHours(2),duration);
    }

    @Test
    public void getWorkoutCals() {
        int cals = workoutSummaryService.getWorkoutCals(siteUser,start,end);
        Assert.assertEquals(2460,cals);
    }

    @Test
    public void getWorkoutStatsBySeason(){
        C2Season season = C2Season.SEASON2022;
        WorkoutStats workoutStats = workoutSummaryService.getWorkoutStatsBySiteUser(siteUser,season);

        Assert.assertEquals(26500,workoutStats.totalMeters().longValue());
    }

    @Test
    public void getWorkStatsBySiteUserPerDay(){

        String year = "2022";
        String month = "4";

        LocalDate start = Utils.getStartDate(year, month);
        LocalDate end = Utils.getEndDate(year,month);

        List<WorkoutStats> workoutStats = workoutSummaryService.getWorkoutStatsBySiteUserPerDay(siteUser,start,end);

        Assert.assertEquals(3,workoutStats.size());

        Assert.assertEquals(5000,workoutStats.get(0).totalMeters().longValue());
    }
}
