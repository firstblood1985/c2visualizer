package io.github.firstblood1985.c2visualizer.service.workout.impl;

import io.github.firstblood1985.c2visualizer.dao.WorkoutSummaryRepository;
import io.github.firstblood1985.c2visualizer.domain.user.SiteUser;
import io.github.firstblood1985.c2visualizer.domain.workout.Season;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummary;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummaryFilter;
import io.github.firstblood1985.c2visualizer.service.workout.WorkoutSummaryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * usage of this class: WorkoutSummaryServiceImpl
 * created by limin @ 2022/4/22
 */
public class WorkoutSummaryServiceImpl implements WorkoutSummaryService {
    @Autowired
    WorkoutSummaryRepository workoutSummaryRepository;

    private final LocalTime STARTTIME = LocalTime.of(0,0);
    private final LocalTime ENDTIME = LocalTime.of(23,59);

    @Override
    public List<WorkoutSummary> getWorkoutSummaryBySiteUser(SiteUser siteUser) {
        return null;
    }

    @Override
    public List<WorkoutSummary> getWorkoutSummaryBySiteUser(SiteUser siteUser,Season season) {
        return null;
    }

    @Override
    public List<WorkoutSummary> getWorkoutSummaryBySiteUser(SiteUser siteUser, LocalDate start, LocalDate end) {
        String logbookId = siteUser.getC2User().getLogbookId();

        return workoutSummaryRepository.findByLogbookIdAndWorkoutDateTimeBetween(logbookId,
                LocalDateTime.of(start, STARTTIME),LocalDateTime.of(end, ENDTIME));
    }

    @Override
    public Map<LocalDate, Long> getWorkoutCountPerDay(SiteUser siteUser, LocalDate start, LocalDate end) {
        List<WorkoutSummary> workoutSummaries = getWorkoutSummaryBySiteUser(siteUser,start,end);

        return workoutSummaries.stream().collect(groupingBy(WorkoutSummary::getWorkoutDate,counting()));
    }

    @Override
    public Long getWorkoutCount(SiteUser siteUser, LocalDate start, LocalDate end) {
        String logbookId = siteUser.getC2User().getLogbookId();

        return workoutSummaryRepository.countByLogbookIdAndWorkoutDateTimeBetween(logbookId,
                LocalDateTime.of(start, STARTTIME),
                LocalDateTime.of(end, ENDTIME));

    }

    @Override
    public List<WorkoutSummary> getWorkoutSummaryByFilter(SiteUser siteUser, WorkoutSummaryFilter filter) {
        List<WorkoutSummary> workoutSummaries = getWorkoutSummaryBySiteUser(siteUser,filter.getStart(),filter.getEnd());

        return workoutSummaries.stream()
                .filter(w->w.getMeters()>= filter.getMinMeters())
                .filter(w->w.getMeters()<= filter.getMaxMeters())
                .collect(Collectors.toList());
    }

    @Override
    public int getWorkoutMeters(SiteUser siteUser, LocalDate start, LocalDate end) {
        List<WorkoutSummary> workoutSummaries = getWorkoutSummaryBySiteUser(siteUser,start,end);

        return workoutSummaries.stream().mapToInt(w-> w.getMeters()).sum();
    }

    @Override
    public Duration getWorkoutDurations(SiteUser siteUser, LocalDate start, LocalDate end) {
        List<WorkoutSummary> workoutSummaries = getWorkoutSummaryBySiteUser(siteUser,start,end);

        return workoutSummaries.stream().map(WorkoutSummary::getWorkoutDuration).reduce(Duration.ZERO,(duration, duration2) -> duration.plus(duration2));
    }

    @Override
    public int getWorkoutCals(SiteUser siteUser, LocalDate start, LocalDate end) {
        List<WorkoutSummary> workoutSummaries = getWorkoutSummaryBySiteUser(siteUser,start,end);

        return workoutSummaries.stream().mapToInt(w-> w.getCalories()).sum();
    }


}
