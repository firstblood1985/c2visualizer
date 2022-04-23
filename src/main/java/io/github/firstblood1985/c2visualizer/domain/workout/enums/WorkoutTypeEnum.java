package io.github.firstblood1985.c2visualizer.domain.workout.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WorkoutTypeEnum implements Convertable {

    FIXED_DISTANCE("Fixed Distance");

    private String workoutType;

    @Override
    public String getCode() {
        return workoutType;
    }

}
