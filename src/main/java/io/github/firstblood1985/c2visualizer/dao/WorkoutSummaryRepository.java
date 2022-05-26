package io.github.firstblood1985.c2visualizer.dao;

import io.github.firstblood1985.c2visualizer.domain.workout.enums.WorkOutEnum;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkoutSummaryRepository extends PagingAndSortingRepository<WorkoutSummary, Long> {
    List<WorkoutSummary> findByWorkOut(WorkOutEnum workOut);

    long countByLogbookIdAndWorkoutDateTimeBetween(String logbookId, LocalDateTime workoutDateTimeStart, LocalDateTime workoutDateTimeEnd);

    List<WorkoutSummary> findByLogbookIdAndWorkoutDateTimeBetween(String logbookId, LocalDateTime workoutDateTimeStart, LocalDateTime workoutDateTimeEnd, Pageable pageable);

    WorkoutSummary findByLogId(String logId);

}