package io.github.firstblood1985.c2visualizer.domain.workout;

import com.google.gson.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Type;
import java.time.LocalDate;

/**
 * usage of this class: WorkoutStatsDTO
 * created by limin @ 2022/5/6
 */
@Data
public class WorkoutStatsDTO {
    private LocalDate start;

    private LocalDate end;

    private Long numberOfWorkouts;

    private Long numberOfDays;

    private Long numberOfActiveDays;

    private Long totalMeters;

    private Long totalCals;

    private Long avgMeters;

    private Long avgMetersActiveDay;

    public WorkoutStatsDTO(WorkoutStats workoutStats)
    {
        BeanUtils.copyProperties(workoutStats,this);
    }


    public String toJson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class,new LocalDateSerializer());
        return gsonBuilder.create().toJson(this);
    }

    private static class LocalDateSerializer implements JsonSerializer<LocalDate> {

        @Override
        public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(localDate.toString());
        }
    }

}
