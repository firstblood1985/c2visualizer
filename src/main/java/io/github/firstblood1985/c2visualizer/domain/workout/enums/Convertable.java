package io.github.firstblood1985.c2visualizer.domain.workout.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * usage of this interface: Convertable
 * created by limin @ 2022/4/20
 */
public interface Convertable {

    String getCode();

    default Convertable of(String s) {
        if(StringUtils.equals(getCode(),s))
            return this;
        else
            return null;
    }
}
