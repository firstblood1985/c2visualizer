package io.github.firstblood1985.c2visualizer.domain.analysis.filter;

import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummary;

/**
 * usage of this class: PaceFilter
 * created by limin @ 2022/5/15
 */
public class PaceFilter extends AnalysisFilter{
    public PaceFilter(String paceFilterRaw) {
        super(FilterTarget.PACE, paceFilterRaw);
    }

    @Override
    public Boolean filter(WorkoutSummary workoutSummary) {
        String pace = workoutSummary.getPace();

        if(operator == FilterOperator.BETWEEN)
            return pace.compareTo(params.get(1))>=0 && pace.compareTo(params.get(0))<=0;

        if(operator == FilterOperator.GET)
            return pace.compareTo(params.get(0))<=0;


        if(operator == FilterOperator.LET)
            return pace.compareTo(params.get(0))>=0;

        return false;
    }
}
