package io.github.firstblood1985.c2visualizer.dao;

import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutDetail;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * usage of this interface: WorkoutDetailRepository
 * created by limin @ 2022/5/20
 */
public interface WorkoutDetailRepository extends PagingAndSortingRepository<WorkoutDetail, Long> {
    List<WorkoutDetail> findByWorkoutSummary_LogId(String logId);

    List<WorkoutDetail> findByMeters(Integer meters);


}
