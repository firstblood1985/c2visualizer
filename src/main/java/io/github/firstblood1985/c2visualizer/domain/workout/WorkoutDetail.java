package io.github.firstblood1985.c2visualizer.domain.workout;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalTime;

/**
 * usage of this class: WorkoutDetail
 * created by limin @ 2022/4/20
 */
@Entity
@Data
public class WorkoutDetail extends WorkoutBaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Convert(converter = DurationToStringConverter.class)
    private Duration duration;

    private Integer meters;

    private String pace;

    private Integer watts;

    private Integer calPerHour;

    private Integer strokeRate;

    private Integer heartRate;

    @ManyToOne
    @JoinColumn(name="log_id",referencedColumnName = "logId")
    private WorkoutSummary workoutSummary;

}
