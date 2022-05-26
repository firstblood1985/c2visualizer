package io.github.firstblood1985.c2visualizer.service.analysis;

import io.github.firstblood1985.c2visualizer.domain.analysis.PaceAnalysisRequest;
import io.github.firstblood1985.c2visualizer.domain.analysis.PaceItem;
import io.github.firstblood1985.c2visualizer.domain.user.SiteUser;

import java.util.List;

/**
 * usage of this interface: C2AnalysisService
 * created by limin @ 2022/5/15
 */
public interface C2AnalysisService {

    List<PaceItem> analysisPace(SiteUser siteUser, PaceAnalysisRequest paceAnalysisRequest);
}
