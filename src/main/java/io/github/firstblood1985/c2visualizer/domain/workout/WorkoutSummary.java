package io.github.firstblood1985.c2visualizer.domain.workout;

import io.github.firstblood1985.c2visualizer.common.util.Utils;
import io.github.firstblood1985.c2visualizer.domain.workout.enums.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * usage of this class: WorktSummary
 * created by limin @ 2022/4/20
 */
@Getter
@Setter
@Entity
public class WorkoutSummary extends WorkoutBaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDateTime workoutDateTime;
    private WorkOutEnum workOut;
    private WorkoutTypeEnum workoutType;
    private WeightClass weightClass;
    private YESNOEnum verified;
    private YESNOEnum ranked;
    private String entered;
    private Integer meters;
    @Convert(converter = DurationToStringConverter.class)
    private  Duration duration;
    private String pace;
    private Integer calories;
    private Integer averageHeartRate;
    private Integer averageWatts;
    private Integer calPerHour;
    private Integer strokeRate;
    private Integer strokeCount;
    private Integer dragFactor;
    private String logbookId;
    private String logId;
    private String url;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "workoutSummary")
    private List<WorkoutDetail> workoutDetails;

    public LocalDate getWorkoutDate() {
        return workoutDateTime.toLocalDate();
    }

    public Integer getSpeed(){
        return Utils.paceToSpeed(pace);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "createTime = " + createTime + ", " +
                "updateTime = " + updateTime + ", " +
                "workoutDateTime = " + workoutDateTime + ", " +
                "workOut = " + workOut + ", " +
                "workoutType = " + workoutType + ", " +
                "weightClass = " + weightClass + ", " +
                "verified = " + verified + ", " +
                "ranked = " + ranked + ", " +
                "entered = " + entered + ", " +
                "meters = " + meters + ", " +
                "duration = " + duration + ", " +
                "pace = " + pace + ", " +
                "calories = " + calories + ", " +
                "averageHeartRate = " + averageHeartRate + ", " +
                "averageWatts = " + averageWatts + ", " +
                "calPerHour = " + calPerHour + ", " +
                "strokeRate = " + strokeRate + ", " +
                "strokeCount = " + strokeCount + ", " +
                "DragFactor = " + dragFactor + ", " +
                "logbookId = " + logbookId + ", " +
                "logId = " + logId + ", " +
                "url = " + url + ")";
    }
}
