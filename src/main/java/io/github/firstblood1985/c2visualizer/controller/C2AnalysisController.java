package io.github.firstblood1985.c2visualizer.controller;

import io.github.firstblood1985.c2visualizer.common.api.CommonResult;
import io.github.firstblood1985.c2visualizer.common.util.Utils;
import io.github.firstblood1985.c2visualizer.domain.analysis.PaceAnalysisRequest;
import io.github.firstblood1985.c2visualizer.domain.analysis.PaceAnalysisRequestRaw;
import io.github.firstblood1985.c2visualizer.domain.user.SiteUser;
import io.github.firstblood1985.c2visualizer.service.analysis.C2AnalysisService;
import io.github.firstblood1985.c2visualizer.service.user.SiteUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * usage of this class: C2AnalysisController
 * created by limin @ 2022/5/15
 */
@Api(tags = "this analysis conntroller")
@RestController
@RequestMapping("/api/analysis")
@Slf4j
public class C2AnalysisController {

    @Autowired
    C2AnalysisService c2AnalysisService;

    @Autowired
    SiteUserService siteUserService;

    @ApiOperation("paceAnalysis")
    @RequestMapping(value = "/pace",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult paceAnalysis(@RequestBody PaceAnalysisRequestRaw paceAnalysisRequestRaw)
    {
        String username = Utils.getCurrentSiteUsername();

        SiteUser siteUser = siteUserService.findSiteUserByUsername(username);
        if (siteUser.getC2User() == null) {
            log.warn("site user is not connected with c2 user, no season summary. site user: {}", siteUser);
            return CommonResult.failed("no connected c2 user found");
        }

        PaceAnalysisRequest paceAnalysisRequest = new PaceAnalysisRequest(paceAnalysisRequestRaw);
        log.info("PaceAnalysisRequest: {}",paceAnalysisRequest);

        return CommonResult.success(c2AnalysisService.analysisPace(siteUser,paceAnalysisRequest));
    }

}
