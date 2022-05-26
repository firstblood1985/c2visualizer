package io.github.firstblood1985.c2visualizer.domain.analysis.timerange;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum TimeRangeType {
    SEASON("Season"),
    STARTEND("Start-End"),
    PASTDAYS("PastDays");

    private String code;

    public static TimeRangeType of(String code) {
        Optional<TimeRangeType> timeRangeType = Arrays.stream(TimeRangeType.values())
                .filter(t -> t.getCode().equals(code)).findAny();

        return timeRangeType.isPresent()?timeRangeType.get():null;

    }
}
