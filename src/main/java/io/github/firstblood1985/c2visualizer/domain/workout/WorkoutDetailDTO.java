package io.github.firstblood1985.c2visualizer.domain.workout;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.firstblood1985.c2visualizer.common.util.Utils;
import lombok.Data;

/**
 * usage of this class: WorkoutDetailDTO
 * created by limin @ 2022/5/22
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class WorkoutDetailDTO {
    private String duration;

    private String meters;

    private String pace;

    private Integer watts;

    private Integer calPerHour;

    private Integer strokeRate;

    private Integer heartRate;

    public WorkoutDetailDTO(WorkoutDetail workoutDetail) {
        duration = Utils.durationToString(workoutDetail.getDuration());
        meters = Utils.metersFormat(workoutDetail.getMeters());
        pace = workoutDetail.getPace();
        watts = workoutDetail.getWatts();
        calPerHour = workoutDetail.getCalPerHour();
        strokeRate = workoutDetail.getStrokeRate();
        heartRate = workoutDetail.getHeartRate();
    }

}
