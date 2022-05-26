package io.github.firstblood1985.c2visualizer.domain.analysis.filter;

import io.github.firstblood1985.c2visualizer.common.util.Utils;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummary;

/**
 * usage of this class: DurationFilter
 * created by limin @ 2022/5/15
 */
public class DurationFilter extends SimpleFilter {

    public DurationFilter( String filterRaw) {
        super(FilterTarget.DURATION, filterRaw);
    }

    @Override
    public Boolean filter(WorkoutSummary workoutSummary) {
        return super.filter(Utils.durationToMinutes(workoutSummary.getDuration()));
    }
}
