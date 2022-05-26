package io.github.firstblood1985.c2visualizer.domain.workout;

import io.github.firstblood1985.c2visualizer.common.util.Initable;
import io.github.firstblood1985.c2visualizer.common.util.Utils;
import io.github.firstblood1985.c2visualizer.domain.BaseModel;
import lombok.Data;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * usage of this class: WorkoutSeasonSummary
 * created by limin @ 2022/5/6
 */
public class WorkoutStats  {

    private final List<WorkoutSummary> workoutSummaries;

    @Getter
    private final LocalDate start;

    @Getter
    private final LocalDate end;

    @Initable
    private Long numberOfWorkouts;

    @Initable
    private Long numberOfDays;

    @Initable
    private Long numberOfActiveDays;

    @Initable
    private Long totalMeters;

    @Initable
    private Long totalCals;

    @Initable
    private Long avgMeters;

    @Initable
    private Long avgMetersActiveDay;

    public WorkoutStats(List<WorkoutSummary> workoutSummaries, LocalDate start, LocalDate end) {
        this.workoutSummaries = workoutSummaries;
        this.start = start;
        this.end = end;

        Utils.initFields(WorkoutStats.class,this);

    }


    public Long numberOfWorkouts() {
        return getOrInit(numberOfWorkouts, () -> Long.valueOf(workoutSummaries.size()));
    }

    public Long numberOfDays() {
        return getOrInit(numberOfDays, () -> {
            return ChronoUnit.DAYS.between(start,end)+1;
        });
    }

    public Long numberOfActiveDays() {
        return getOrInit(numberOfActiveDays, () -> {
            Map<LocalDate, Long> workoutDays = workoutSummaries.stream().collect(groupingBy(WorkoutSummary::getWorkoutDate, counting()));
            return Long.valueOf(workoutDays.keySet().size());
        });
    }

    public Long totalMeters() {
        return getOrInit(totalMeters, () -> workoutSummaries.stream().mapToLong(WorkoutSummary::getMeters).sum());
    }

    public Long totalCals() {
        return getOrInit(totalCals, () -> workoutSummaries.stream().mapToLong(WorkoutSummary::getCalories).sum());
    }

    public Long avgMeters() {
        return getOrInit(avgMeters, () ->  numberOfDays()!=0 ? totalMeters() / numberOfDays():0);
    }

    public Long avgMetersActiveDay() {
        return getOrInit(avgMetersActiveDay, () -> numberOfActiveDays() !=0 ?totalMeters() / numberOfActiveDays():0);
    }

    private Long getOrInit(Long field, Supplier<Long> init) {
        if (field == null) {
            field = init.get();
        }
        return field;
    }

    @Override
    public String toString() {
        return "WorkoutStats{" +
                "start=" + start +
                ", end=" + end +
                ", numberOfWorkouts=" + numberOfWorkouts() +
                ", numberOfDays=" + numberOfDays() +
                ", numberOfActiveDays=" + numberOfActiveDays() +
                ", totalMeters=" + totalMeters() +
                ", totalCals=" + totalCals() +
                ", avgMeters=" + avgMeters() +
                ", avgMetersActiveDay=" + avgMetersActiveDay() +
                '}';
    }
}