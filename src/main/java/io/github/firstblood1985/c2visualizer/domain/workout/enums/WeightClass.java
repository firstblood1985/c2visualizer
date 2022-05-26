package io.github.firstblood1985.c2visualizer.domain.workout.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WeightClass implements Convertable {
    HEAVY_WEIGHT("Hwt"),
    LIGHT_WEIGHT("Lwt");

    private String weightClass;

    @Override
    public String getCode() {
        return weightClass;
    }
}
