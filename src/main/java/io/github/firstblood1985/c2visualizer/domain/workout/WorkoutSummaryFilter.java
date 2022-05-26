package io.github.firstblood1985.c2visualizer.domain.workout;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * usage of this class: WorkoutSummaryFilter
 * created by limin @ 2022/4/22
 */
@Getter
@Setter
@Builder
public class WorkoutSummaryFilter {

   private LocalDate start;

   private LocalDate end;

   private Integer minMeters = 0;

   private Integer maxMeters = Integer.MAX_VALUE;
}
