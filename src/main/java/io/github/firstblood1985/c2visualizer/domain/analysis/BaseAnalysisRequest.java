package io.github.firstblood1985.c2visualizer.domain.analysis;

import io.github.firstblood1985.c2visualizer.domain.analysis.filter.AnalysisFilter;
import io.github.firstblood1985.c2visualizer.domain.analysis.filter.FilterTarget;
import io.github.firstblood1985.c2visualizer.domain.analysis.timerange.TimeRangeType;
import io.github.firstblood1985.c2visualizer.domain.workout.C2Season;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummary;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * usage of this class: BaseAnalysisRequest
 * created by limin @ 2022/5/15
 */
@Getter
@Setter
public abstract class BaseAnalysisRequest {

    protected LocalDate startDate;

    protected LocalDate endDate;

    protected List<AnalysisFilter> analysisFilters;

    protected BaseAnalysisRequest(AnalysisRequestRaw raw) {
        analysisFilters = new ArrayList<>();

        if (raw.getStartDate() != null && raw.getEndDate() != null) {

            //date format: yyyy-MM-dd
            startDate = LocalDate.parse(raw.getStartDate());
            endDate = LocalDate.parse(raw.getEndDate());
            return;
        }

        if (raw.getTimeRange() != null && raw.getTimeRangeType() != null) {
            startDate = getTimeRangeStart(raw.getTimeRange(), raw.getTimeRangeType());
            endDate = getTimeRangeEnd(raw.getTimeRange(), raw.getTimeRangeType());
            return;
        }
    }

    protected void buildFilters(Object rawRequest) {
        Field[] fields = rawRequest.getClass().getDeclaredFields();

        for (Field field : fields) {
            FilterTarget filterTarget = FilterTarget.of(field.getName());
            if (filterTarget != null) {
                try {
                    field.setAccessible(true);
                    String value = (String) field.get(rawRequest);
                    if (!StringUtils.isEmpty(value) && !value.contains("undefined")) {
                        analysisFilters.add(AnalysisFilter.of(filterTarget, (String) field.get(rawRequest)));
                    }
                } catch (IllegalAccessException e) {
                }
            }
        }
    }

    public Boolean filter(WorkoutSummary workoutSummary) {
        Boolean result = true;

        for (AnalysisFilter filter : analysisFilters) {
            result = result && filter.filter(workoutSummary);
            if (!result)
                return false;
        }
        return true;
    }

    private LocalDate getTimeRangeStart(String timeRange, String timeRangeType) {
        if (TimeRangeType.of(timeRangeType) == TimeRangeType.SEASON) {
            C2Season c2Season = C2Season.of(timeRange);
            return c2Season.seasonStart();
        }

        return null;
    }

    private LocalDate getTimeRangeEnd(String timeRange, String timeRangeType) {
        if (TimeRangeType.of(timeRangeType) == TimeRangeType.SEASON) {
            C2Season c2Season = C2Season.of(timeRange);
            return c2Season.seasonEnd();
        }

        return null;
    }
}
