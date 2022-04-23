package io.github.firstblood1985.c2visualizer.domain.workout.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * usage of this class: WorkOutEnum
 * created by limin @ 2022/4/20
 */
@Getter
@AllArgsConstructor
public enum WorkOutEnum implements Convertable {
    ROWERG("RowErg");

    private String workOut;

    @Override
    public String getCode() {
        return workOut;
    }

    @Override
    public Convertable of(String s) {
        if(StringUtils.equals(s,workOut))
            return this;
        return null;
    }
}
