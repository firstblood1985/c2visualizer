package io.github.firstblood1985.c2visualizer.domain.workout;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

/**
 * usage of this class: WorkoutDetail
 * created by limin @ 2022/4/20
 */
@Getter
@Setter
@Entity
public class WorkoutDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Convert(converter = LocalTimeConverter.class)
    private LocalTime duration;

    private Integer meters;

    private String pace;

    private Integer watts;

    private Integer calPerHour;

    private Integer strokeRate;

    private Integer heartRate;

    @ManyToOne
    @JoinColumn(name = "workout_summary_id")
    private WorkoutSummary workoutSummary;

}
