package io.github.firstblood1985.c2visualizer.domain.analysis;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * usage of this class: PaceAnalysisRequest
 * created by limin @ 2022/5/15
 */
@Getter
@Setter
@ToString
public class PaceAnalysisRequest extends BaseAnalysisRequest{

    public PaceAnalysisRequest(PaceAnalysisRequestRaw raw)
    {
        super(raw);
        buildFilters(raw);
    }


}
