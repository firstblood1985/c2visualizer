package io.github.firstblood1985.c2visualizer.domain.analysis.filter;

import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummary;

/**
 * usage of this class: WorkoutDateFilter
 * created by limin @ 2022/5/15
 */
public class WorkoutDateFilter extends SimpleFilter {

    public WorkoutDateFilter(String filterRaw) {
        super(FilterTarget.WORKOUTDATE, filterRaw);
    }

    @Override
    public Boolean filter(WorkoutSummary workoutSummary) {
        return super.filter(workoutSummary.getWorkoutDate().toString());
    }
}
