package io.github.firstblood1985.c2visualizer.domain.workout.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Verified implements Convertable {
    YES("Yes"),
    NO("No");

    private String verified;

    @Override
    public String getCode() {
       return verified;
    }
}
