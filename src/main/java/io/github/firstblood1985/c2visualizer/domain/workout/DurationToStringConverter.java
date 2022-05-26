package io.github.firstblood1985.c2visualizer.domain.workout;

import io.github.firstblood1985.c2visualizer.common.util.Utils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * usage of this class: LocalTimeConverter
 * created by limin @ 2022/4/20
 */
@Converter
public class DurationToStringConverter implements AttributeConverter<Duration, String> {

    @Override
    public String convertToDatabaseColumn(Duration duration) {
        if (null == duration)
            return null;
        return duration.toString();
    }

    @Override
    public Duration convertToEntityAttribute(String s) {
        return Utils.StringToDuration(s);
    }
}
