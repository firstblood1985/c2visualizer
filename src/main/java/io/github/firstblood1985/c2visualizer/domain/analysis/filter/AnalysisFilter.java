package io.github.firstblood1985.c2visualizer.domain.analysis.filter;

import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummary;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * usage of this class: AnalysisFilter
 * created by limin @ 2022/5/15
 */
@Getter
@Setter
@ToString
public abstract class AnalysisFilter {
    protected final FilterTarget filterTarget;

    protected FilterOperator operator;

    protected List<String> params;

    protected AnalysisFilter(FilterTarget filterTarget, String filterRaw) {
        this.filterTarget = filterTarget;
        params = new ArrayList<>();
        parseFilterRaw(filterRaw);
    }

    public abstract Boolean filter(WorkoutSummary workoutSummary);


    private void parseFilterRaw(String filterRaw) {
        /**
         * filterRaw format:
         * BETWEEN {lowEnd} {highEnd}
         * LET {lowEnd}
         * GET {highEnd}
         */
        if (StringUtils.isEmpty(filterRaw)) return;
        String[] filters = filterRaw.split(" ");
        operator = FilterOperator.valueOf(filters[0]);
        params.addAll(Arrays.asList(filters).subList(1, filters.length));

    }

    public static AnalysisFilter of(FilterTarget filterTarget,String filterRaw)
    {
        if(filterTarget == FilterTarget.PACE)
            return new PaceFilter(filterRaw);

        if(filterTarget == FilterTarget.DISTANCE)
            return new DistanceFilter(filterRaw);

        if(filterTarget == FilterTarget.DURATION)
            return new DurationFilter(filterRaw);

        if(filterTarget == FilterTarget.WORKOUTDATE)
            return new WorkoutDateFilter(filterRaw);
        return null;
    }

}
