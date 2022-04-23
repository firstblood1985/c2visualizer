package io.github.firstblood1985.c2visualizer.domain.workout;

import io.github.firstblood1985.c2visualizer.domain.workout.enums.Ranked;
import io.github.firstblood1985.c2visualizer.domain.workout.enums.Verified;
import io.github.firstblood1985.c2visualizer.domain.workout.enums.WorkOutEnum;
import io.github.firstblood1985.c2visualizer.domain.workout.enums.WorkoutTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

/**
 * usage of this class: WorktSummary
 * created by limin @ 2022/4/20
 */
@Getter
@Setter
@Entity
public class WorkoutSummary extends WorkoutBaseModel {

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime workoutDateTime;

    private String logbookId;

    private String logId;

    private WorkOutEnum workOut;

    private WorkoutTypeEnum workoutType;

    private Verified verified;

    private Ranked ranked;

    private String entered;

    private Integer averageWatts;

    private Integer calPerHour;

    private Integer strokeRate;

    private Integer strokeCount;

    private Integer DragFactor;

    private Integer meters;

    @Convert(converter = LocalTimeConverter.class)
    private LocalTime duration;

    private String pace;

    private Integer calories;

    private Integer averageHeartRate;

    @OneToMany(mappedBy = "workoutSummary")
    private Set<WorkoutDetail> workoutDetails;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public LocalDate getWorkoutDate(){
        return workoutDateTime.toLocalDate();
    }

    public Duration getWorkoutDuration(){
        return Duration.between(duration,LocalTime.of(0,0));
    }

}
