package io.github.firstblood1985.c2visualizer.domain.workout;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.firstblood1985.c2visualizer.common.util.Utils;
import lombok.Data;
import org.springframework.util.CollectionUtils;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * usage of this class: WorkoutSummaryDTO
 * created by limin @ 2022/5/12
 */
@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class WorkoutSummaryDTO {
    private LocalDate date;
    private String meters;

    private String duration;

    private String pace;

    private String logId;

    /////////////////////////////////////////
    private String workout;
    private String workoutType;
    private Integer calories;
    private Integer averageHeartRate;
    private Integer averageWatts;
    private Integer calPerHour;
    private Integer strokeRate;
    private Integer strokeCount;
    private Integer dragFactor;

    private List<WorkoutDetailDTO> workoutDetail;

    public WorkoutSummaryDTO(WorkoutSummary workoutSummary)
    {
        meters = Utils.metersFormat(workoutSummary.getMeters());
        date = workoutSummary.getWorkoutDate();
        pace = workoutSummary.getPace();
        duration = Utils.durationToString(workoutSummary.getDuration());
        logId = workoutSummary.getLogId();
        workout = workoutSummary.getWorkOut().getCode();
        workoutType  = workoutSummary.getWorkoutType().getCode();

        calories = workoutSummary.getCalories();
        averageHeartRate = workoutSummary.getAverageHeartRate();
        averageWatts = workoutSummary.getAverageWatts();
        calPerHour = workoutSummary.getCalPerHour();
        strokeRate = workoutSummary.getStrokeRate();
        strokeCount = workoutSummary.getStrokeCount();
        dragFactor = workoutSummary.getDragFactor();

        if(!CollectionUtils.isEmpty(workoutSummary.getWorkoutDetails()))
            workoutDetail = workoutSummary.getWorkoutDetails().stream()
                    .map(WorkoutDetailDTO::new).collect(Collectors.toList());
    }


}
