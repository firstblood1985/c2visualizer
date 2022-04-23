package io.github.firstblood1985.c2visualizer.domain.workout.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Ranked implements Convertable {
    NO("No"),
    YES("Yes");
    private String ranked;

    @Override
    public String getCode() {
       return ranked;
    }
}
