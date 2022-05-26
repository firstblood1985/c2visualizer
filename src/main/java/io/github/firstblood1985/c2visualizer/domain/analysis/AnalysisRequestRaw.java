package io.github.firstblood1985.c2visualizer.domain.analysis;

import lombok.Getter;
import lombok.Setter;

/**
 * usage of this class: AnalysisRequestRaw
 * created by limin @ 2022/5/15
 */
@Getter
@Setter
public abstract class AnalysisRequestRaw {

    protected String startDate;

    protected String endDate;

    protected String timeRange;

    protected String timeRangeType;
}
