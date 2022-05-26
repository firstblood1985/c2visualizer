package io.github.firstblood1985.c2visualizer.domain.analysis.filter;

import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummary;

/**
 * usage of this class: DistanceFilter
 * created by limin @ 2022/5/15
 */
public class DistanceFilter extends SimpleFilter {
    public DistanceFilter(String filterRaw) {
        super(FilterTarget.DISTANCE, filterRaw);
    }

    @Override
    public Boolean filter(WorkoutSummary workoutSummary) {
        return super.filter(workoutSummary.getMeters());
    }
}
