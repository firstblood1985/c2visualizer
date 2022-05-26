package io.github.firstblood1985.c2visualizer.domain.analysis.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@Getter
public enum FilterTarget {
    WORKOUTDATE("workoutDateFilterRaw"),
    PACE("paceFilterRaw"),
    DURATION("durationFilterRaw"),
    DISTANCE("distanceFilterRaw");

    private String filterRaw;

    public static FilterTarget of(String filterRaw) {

        Optional<FilterTarget> f = Arrays.stream(FilterTarget.values()).filter(filterTarget -> filterTarget.getFilterRaw().equals(filterRaw)).findAny();

        return f.isPresent()?f.get():null;
    }
}
