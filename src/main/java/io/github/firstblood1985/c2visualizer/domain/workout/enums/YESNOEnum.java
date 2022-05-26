package io.github.firstblood1985.c2visualizer.domain.workout.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum YESNOEnum implements Convertable{
    NO("No"),
    YES("Yes"),
    NULL("NULL");

    private String result;
    @Override
    public String getCode() {
        return result;
    }
}
