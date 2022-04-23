package io.github.firstblood1985.c2visualizer.domain.workout;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * usage of this class: LocalTimeConverter
 * created by limin @ 2022/4/20
 */
@Converter
public class LocalTimeConverter implements AttributeConverter<LocalTime, String> {
    private final DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("H:mm:ss.S");
    private final DateTimeFormatter formatterMinute = DateTimeFormatter.ofPattern("m:ss.S");

    @Override
    public String convertToDatabaseColumn(LocalTime localTime) {
        if (null == localTime)
            return null;

        if (localTime.isAfter(LocalTime.of(1, 0)))
            return formatterHour.format(localTime);
        else
            return formatterMinute.format(localTime);
    }

    @Override
    public LocalTime convertToEntityAttribute(String s) {
        if (StringUtils.isEmpty(s))
            return null;
        try {
            return LocalTime.parse(s, formatterHour);
        } catch (Exception e) {
            return LocalTime.parse(s, formatterMinute);
        }
    }
}
