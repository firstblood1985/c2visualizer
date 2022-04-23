package io.github.firstblood1985.c2visualizer.domain.workout;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * usage of this class: LocalDateTimeConverter
 * created by limin @ 2022/4/20
 */
@Converter
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, String> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm:ss",Locale.US);

    @Override
    public String convertToDatabaseColumn(LocalDateTime locDate) {
        return (locDate == null ? null : formatter.format(locDate));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(String dateValue) {
        return (dateValue == null ? null : LocalDateTime.parse(dateValue, formatter));
    }
}
