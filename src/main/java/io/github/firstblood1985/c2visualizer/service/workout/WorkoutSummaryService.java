package io.github.firstblood1985.c2visualizer.service.workout;

import io.github.firstblood1985.c2visualizer.domain.user.SiteUser;
import io.github.firstblood1985.c2visualizer.domain.workout.Season;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummary;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummaryFilter;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * usage of this interface: WorkoutSummaryService
 * created by limin @ 2022/4/22
 */
public interface WorkoutSummaryService {

    List<WorkoutSummary> getWorkoutSummaryBySiteUser(SiteUser siteUser);

    List<WorkoutSummary> getWorkoutSummaryBySiteUser(SiteUser siteUser,Season season);

    List<WorkoutSummary> getWorkoutSummaryBySiteUser(SiteUser siteUser, LocalDate start, LocalDate end);

    Map<LocalDate,Long> getWorkoutCountPerDay(SiteUser siteUser, LocalDate start,LocalDate end);

    Long getWorkoutCount(SiteUser siteUser,LocalDate start, LocalDate end);

    List<WorkoutSummary> getWorkoutSummaryByFilter(SiteUser siteUser, WorkoutSummaryFilter filter);

    int getWorkoutMeters(SiteUser siteUser,LocalDate start, LocalDate end);

    Duration getWorkoutDurations(SiteUser siteUser, LocalDate start, LocalDate end);

    int getWorkoutCals(SiteUser siteUser,LocalDate start, LocalDate end);
}
