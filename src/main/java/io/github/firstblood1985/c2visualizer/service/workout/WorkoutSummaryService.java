package io.github.firstblood1985.c2visualizer.service.workout;

import io.github.firstblood1985.c2visualizer.domain.user.SiteUser;
import io.github.firstblood1985.c2visualizer.domain.workout.C2Season;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutStats;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummary;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummaryFilter;
import org.springframework.data.domain.Pageable;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * usage of this interface: WorkoutSummaryService
 * created by limin @ 2022/4/22
 */
public interface WorkoutSummaryService {


    WorkoutSummary getWorkoutSummaryWithDetails(SiteUser siteUser, String logId);

    List<WorkoutStats> getWorkoutStatsBySiteUserPerDay(SiteUser siteUser, LocalDate start,LocalDate end);

    WorkoutStats getWorkoutStatsBySiteUser(SiteUser siteUser, LocalDate start, LocalDate end);

    WorkoutStats getWorkoutStatsBySiteUser(SiteUser siteUser, C2Season season);

    List<WorkoutSummary> getWorkoutSummaryBySiteUser(SiteUser siteUser, LocalDate start, LocalDate end, Pageable pageable);

    Map<LocalDate,Long> getWorkoutCountPerDay(SiteUser siteUser, LocalDate start,LocalDate end);

    Long getWorkoutCount(SiteUser siteUser,LocalDate start, LocalDate end);

    List<WorkoutSummary> getWorkoutSummaryByFilter(SiteUser siteUser, WorkoutSummaryFilter filter);

    int getWorkoutMeters(SiteUser siteUser,LocalDate start, LocalDate end);

    Duration getWorkoutDurations(SiteUser siteUser, LocalDate start, LocalDate end);

    int getWorkoutCals(SiteUser siteUser,LocalDate start, LocalDate end);


    default List<WorkoutSummary> getWorkoutSummaryBySiteUser(SiteUser siteUser, LocalDate start, LocalDate end){
        return getWorkoutSummaryBySiteUser(siteUser,start,end,Pageable.unpaged());
    }
}
