package io.github.firstblood1985.c2visualizer.domain.workout.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * usage of this class: C2Converter
 * created by limin @ 2022/4/20
 */
@Converter(autoApply = true)
public class C2Converter implements AttributeConverter<Convertable, String> {

    private static List<Convertable> convertables = new ArrayList<>();

    static {
        convertables.addAll(Arrays.asList(WorkOutEnum.values()));
        convertables.addAll(Arrays.asList(WeightClass.values()));
        convertables.addAll(Arrays.asList(WorkoutTypeEnum.values()));
    }

    @Override
    public String convertToDatabaseColumn(Convertable convertable) {
        if (null == convertable)
            return null;
        else
            return convertable.getCode();
    }

    @Override
    public Convertable convertToEntityAttribute(String s) {
        Optional<Convertable> r = convertables.stream().map(c -> c.of(s)).filter(m->m!=null).findAny();
        if(r.isPresent())
            return r.get();
        else
            throw new IllegalArgumentException();
    }

}
