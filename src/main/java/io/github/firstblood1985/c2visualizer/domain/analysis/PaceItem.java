package io.github.firstblood1985.c2visualizer.domain.analysis;

import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummary;
import lombok.Data;

import java.time.LocalDate;

/**
 * usage of this class: PaceItem
 * created by limin @ 2022/5/15
 */
@Data
public class PaceItem {
    private LocalDate date;

    private String pace;

    private Integer speed;

    public PaceItem(WorkoutSummary workoutSummary) {
        date = workoutSummary.getWorkoutDate();
        pace = workoutSummary.getPace();

        speed = workoutSummary.getSpeed();

    }

}
