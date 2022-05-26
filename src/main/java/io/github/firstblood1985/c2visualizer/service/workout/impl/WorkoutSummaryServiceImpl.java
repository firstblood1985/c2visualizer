package io.github.firstblood1985.c2visualizer.service.workout.impl;

import io.github.firstblood1985.c2visualizer.dao.WorkoutSummaryRepository;
import io.github.firstblood1985.c2visualizer.domain.user.SiteUser;
import io.github.firstblood1985.c2visualizer.domain.workout.C2Season;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutStats;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummary;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummaryFilter;
import io.github.firstblood1985.c2visualizer.service.workout.WorkoutSummaryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * usage of this class: WorkoutSummaryServiceImpl
 * created by limin @ 2022/4/22
 */
@Service
@Slf4j
public class WorkoutSummaryServiceImpl implements WorkoutSummaryService {
    @Autowired
    WorkoutSummaryRepository workoutSummaryRepository;

    private final LocalTime STARTTIME = LocalTime.of(0, 0);
    private final LocalTime ENDTIME = LocalTime.of(23, 59);

    @Transactional
    private WorkoutSummary getWorkoutSummaryWithDetails(String logId) {
        return  workoutSummaryRepository.findByLogId(logId);
    }

    @Override
    public WorkoutSummary getWorkoutSummaryWithDetails(SiteUser siteUser, String logId) {
        WorkoutSummary workoutSummary = getWorkoutSummaryWithDetails(logId);

        String logbookId = siteUser.getC2User().getLogbookId();

        if (!StringUtils.equals(logbookId, workoutSummary.getLogbookId())) {
            log.warn("cannot query logId not belong to the user: SiteUser: {}, WorkoutSummary: {}", siteUser,workoutSummary);
        }
        return workoutSummary;
    }

    @Override
    public List<WorkoutStats> getWorkoutStatsBySiteUserPerDay(SiteUser siteUser, LocalDate start, LocalDate end) {
        List<WorkoutSummary> workoutSummaries = getWorkoutSummaryBySiteUser(siteUser, start, end);

        Map<LocalDate, List<WorkoutSummary>> perDay = workoutSummaries.stream().collect(groupingBy(WorkoutSummary::getWorkoutDate));

        return perDay.entrySet().stream().map(entry -> new WorkoutStats(entry.getValue(), entry.getKey(), entry.getKey())).collect(Collectors.toList());
    }

    @Override
    public WorkoutStats getWorkoutStatsBySiteUser(SiteUser siteUser, LocalDate start, LocalDate end) {
        List<WorkoutSummary> workoutSummaries = getWorkoutSummaryBySiteUser(siteUser, start, end);

        return new WorkoutStats(workoutSummaries, start, end);
    }

    @Override
    public WorkoutStats getWorkoutStatsBySiteUser(SiteUser siteUser, C2Season season) {
        return getWorkoutStatsBySiteUser(siteUser, season.seasonStart(), season.seasonEnd());
    }


    @Override
    public List<WorkoutSummary> getWorkoutSummaryBySiteUser(SiteUser siteUser, LocalDate start, LocalDate end) {
        String logbookId = siteUser.getC2User().getLogbookId();

        List<WorkoutSummary> workoutSummaryList = workoutSummaryRepository.findByLogbookIdAndWorkoutDateTimeBetween(logbookId,
                LocalDateTime.of(start, STARTTIME), LocalDateTime.of(end, ENDTIME), Pageable.unpaged());

//        workoutSummaryList.sort(Comparator.comparing(WorkoutSummary::getWorkoutDate,Comparator.reverseOrder()).
//                thenComparing(WorkoutSummary::getWorkoutDateTime,Comparator.reverseOrder()));
        workoutSummaryList.sort(Comparator.comparing(WorkoutSummary::getWorkoutDateTime,Comparator.reverseOrder()));

        return workoutSummaryList;
    }

    @Override
    public List<WorkoutSummary> getWorkoutSummaryBySiteUser(SiteUser siteUser, LocalDate start, LocalDate end, Pageable pageable) {
        String logbookId = siteUser.getC2User().getLogbookId();
        return workoutSummaryRepository.findByLogbookIdAndWorkoutDateTimeBetween(logbookId,
                LocalDateTime.of(start, STARTTIME), LocalDateTime.of(end, ENDTIME), pageable);
    }


    @Override
    public Map<LocalDate, Long> getWorkoutCountPerDay(SiteUser siteUser, LocalDate start, LocalDate end) {
        List<WorkoutSummary> workoutSummaries = getWorkoutSummaryBySiteUser(siteUser, start, end);

        return workoutSummaries.stream().collect(groupingBy(WorkoutSummary::getWorkoutDate, counting()));
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
        List<WorkoutSummary> workoutSummaries = getWorkoutSummaryBySiteUser(siteUser, filter.getStart(), filter.getEnd());

        return workoutSummaries.stream()
                .filter(w -> w.getMeters() >= filter.getMinMeters())
                .filter(w -> w.getMeters() <= filter.getMaxMeters())
                .collect(Collectors.toList());
    }

    @Override
    public int getWorkoutMeters(SiteUser siteUser, LocalDate start, LocalDate end) {
        List<WorkoutSummary> workoutSummaries = getWorkoutSummaryBySiteUser(siteUser, start, end);

        return workoutSummaries.stream().mapToInt(w -> w.getMeters()).sum();
    }

    @Override
    public Duration getWorkoutDurations(SiteUser siteUser, LocalDate start, LocalDate end) {
        List<WorkoutSummary> workoutSummaries = getWorkoutSummaryBySiteUser(siteUser, start, end);

        return workoutSummaries.stream().map(WorkoutSummary::getDuration).reduce(Duration.ZERO, (duration, duration2) -> duration.plus(duration2));
    }

    @Override
    public int getWorkoutCals(SiteUser siteUser, LocalDate start, LocalDate end) {
        List<WorkoutSummary> workoutSummaries = getWorkoutSummaryBySiteUser(siteUser, start, end);

        return workoutSummaries.stream().mapToInt(w -> w.getCalories()).sum();
    }


}
