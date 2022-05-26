package io.github.firstblood1985.c2visualizer.domain.analysis;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * usage of this class: PaceAnalysisRequestRaw
 * created by limin @ 2022/5/15
 */
@Data
public class PaceAnalysisRequestRaw extends AnalysisRequestRaw {

    private String durationFilterRaw;

    private String distanceFilterRaw;

    private String paceFilterRaw;

}
