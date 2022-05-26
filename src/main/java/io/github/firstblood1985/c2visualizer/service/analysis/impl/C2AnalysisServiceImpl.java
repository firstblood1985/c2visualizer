package io.github.firstblood1985.c2visualizer.service.analysis.impl;

import io.github.firstblood1985.c2visualizer.domain.analysis.PaceAnalysisRequest;
import io.github.firstblood1985.c2visualizer.domain.analysis.PaceAnalysisResult;
import io.github.firstblood1985.c2visualizer.domain.analysis.PaceItem;
import io.github.firstblood1985.c2visualizer.domain.user.SiteUser;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummary;
import io.github.firstblood1985.c2visualizer.service.analysis.C2AnalysisService;
import io.github.firstblood1985.c2visualizer.service.workout.WorkoutSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * usage of this class: C2AnalysisServiceImpl
 * created by limin @ 2022/5/15
 */
@Service
public class C2AnalysisServiceImpl implements C2AnalysisService {

    @Autowired
    WorkoutSummaryService workoutSummaryService;

    @Override
    public List<PaceItem> analysisPace(SiteUser siteUser, PaceAnalysisRequest paceAnalysisRequest) {

        List<WorkoutSummary> workoutSummaries = workoutSummaryService.getWorkoutSummaryBySiteUser(siteUser,paceAnalysisRequest.getStartDate(),paceAnalysisRequest.getEndDate());

        List<WorkoutSummary> filtered = workoutSummaries.stream().filter(workoutSummary -> paceAnalysisRequest.filter(workoutSummary)).collect(Collectors.toList());

        return filtered.stream().map(PaceItem::new).collect(Collectors.toList());

    }
}
