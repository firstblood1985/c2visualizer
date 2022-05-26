package io.github.firstblood1985.c2visualizer.service.analysis;

import io.github.firstblood1985.c2visualizer.TestCommon;
import io.github.firstblood1985.c2visualizer.domain.analysis.PaceAnalysisRequest;
import io.github.firstblood1985.c2visualizer.domain.analysis.PaceAnalysisRequestRaw;
import io.github.firstblood1985.c2visualizer.domain.analysis.PaceItem;
import io.github.firstblood1985.c2visualizer.domain.user.SiteUser;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummary;
import io.github.firstblood1985.c2visualizer.service.analysis.impl.C2AnalysisServiceImpl;
import io.github.firstblood1985.c2visualizer.service.workout.WorkoutSummaryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * usage of this class: C2AnalysisServiceTest
 * created by limin @ 2022/5/15
 */
@RunWith(SpringRunner.class)
public class C2AnalysisServiceTest {

    @Mock
    WorkoutSummaryService workoutSummaryService;

    List<WorkoutSummary> workoutSummaries;

    SiteUser siteUser;

    LocalDate start;

    LocalDate end;

    PaceAnalysisRequestRaw raw;
    PaceAnalysisRequest paceAnalysisRequest;

    @InjectMocks
    C2AnalysisService c2AnalysisService = new C2AnalysisServiceImpl();
    @Before
    public void setUp() throws Exception {
        workoutSummaries = TestCommon.generateWorkoutSummaries();
        siteUser = TestCommon.generateSiteUser();
        start = LocalDate.of(2022,4,21);
        end = LocalDate.of(2022,4,23);

        raw = new PaceAnalysisRequestRaw();
        raw.setStartDate("2022-04-21");
        raw.setEndDate("2022-04-23");
        raw.setDistanceFilterRaw("GET 10000");
        raw.setDurationFilterRaw("GET 50");
        paceAnalysisRequest = new PaceAnalysisRequest(raw);

        when(workoutSummaryService.getWorkoutSummaryBySiteUser(any(),any(),any())).thenReturn(workoutSummaries);
    }

    @Test
    public void testPaceAnalysis(){
        List<PaceItem> results = c2AnalysisService.analysisPace(siteUser,paceAnalysisRequest);
        Assert.assertEquals(1,results.size());
    }

}
