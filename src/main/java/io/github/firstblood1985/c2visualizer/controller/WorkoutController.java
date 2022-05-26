package io.github.firstblood1985.c2visualizer.controller;

import io.github.firstblood1985.c2visualizer.common.api.CommonResult;
import io.github.firstblood1985.c2visualizer.common.util.Utils;
import io.github.firstblood1985.c2visualizer.domain.user.SiteUser;
import io.github.firstblood1985.c2visualizer.domain.workout.C2Season;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutStatsDTO;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummaryDTO;
import io.github.firstblood1985.c2visualizer.service.user.SiteUserService;
import io.github.firstblood1985.c2visualizer.service.workout.WorkoutSummaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * usage of this class: WorkoutController
 * created by limin @ 2022/5/5
 */
@Api(tags = "this workout conntroller")
@RestController
@RequestMapping("/api/workout")
@Slf4j
public class WorkoutController {

    @Autowired
    WorkoutSummaryService workoutSummaryService;

    @Autowired
    SiteUserService siteUserService;

    @ApiOperation("getSeasonList")
    @RequestMapping(value = "/seasonList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getSeasonList() {
        return CommonResult.success(Arrays.stream(C2Season.values()).map(C2Season::getSeason));
    }

    @ApiOperation("get single workout with details")
    @RequestMapping(value = "/singleWorkout/{logId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getSingleWorkout(@PathVariable String logId) {
        String username = Utils.getCurrentSiteUsername();
        SiteUser siteUser = siteUserService.findSiteUserByUsername(username);

        if (siteUser.getC2User() == null) {
            log.warn("site user is not connected with c2 user, no season summary. site user: {}", siteUser);
            return CommonResult.failed("no connected c2 user found");
        }

        log.info("Querying single workout, logId: {}",logId);
        return CommonResult.success(
                new WorkoutSummaryDTO(workoutSummaryService.getWorkoutSummaryWithDetails(siteUser, logId))
        );
    }


    @ApiOperation("get workout stats by month")
    @RequestMapping(value = "/workoutsByMonth", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getWorkoutsByMonth(@NonNull @RequestParam(value = "year") String year,
                                           @NonNull @RequestParam(value = "month") String month
    ) {
        String username = Utils.getCurrentSiteUsername();
        LocalDate start = Utils.getStartDate(year, month);
        LocalDate end = Utils.getEndDate(year, month);

        SiteUser siteUser = siteUserService.findSiteUserByUsername(username);
        if (siteUser.getC2User() == null) {
            log.warn("site user is not connected with c2 user, no season summary. site user: {}", siteUser);
            return CommonResult.failed("no connected c2 user found");
        }
        log.info("getWorkoutStatsByMonth, site user: {}, year: {}, month: {}", siteUser, year, month);


        List<WorkoutSummaryDTO> workoutSummaryDTOList = workoutSummaryService.getWorkoutSummaryBySiteUser(siteUser, start, end).
                stream().map(WorkoutSummaryDTO::new).collect(Collectors.toList());

        return CommonResult.success(workoutSummaryDTOList);

    }

    @ApiOperation("get workout stats by month per day")
    @RequestMapping(value = "/workoutStatsByMonthPerDay", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getWorkoutStatsByMonthPerDay(
            @NonNull @RequestParam(value = "year") String year,
            @NonNull @RequestParam(value = "month") String month
    ) {
        String username = Utils.getCurrentSiteUsername();
        LocalDate start = Utils.getStartDate(year, month);
        LocalDate end = Utils.getEndDate(year, month);

        SiteUser siteUser = siteUserService.findSiteUserByUsername(username);
        if (siteUser.getC2User() == null) {
            log.warn("site user is not connected with c2 user, no season summary. site user: {}", siteUser);
            return CommonResult.failed("no connected c2 user found");
        }

        log.info("getWorkoutStatsByMonthPerDay, site user: {}, year: {}, month: {}", siteUser, year, month);
        return CommonResult.success(
                workoutSummaryService.getWorkoutStatsBySiteUserPerDay(siteUser, start, end)
                        .stream().map(WorkoutStatsDTO::new).collect(Collectors.toList())
        );
    }


    @ApiOperation("get Season Summary")
    @RequestMapping(value = "/seasonSummary/{year}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getSeasonSummary(@PathVariable String year) {
        String username = Utils.getCurrentSiteUsername();
        C2Season season;
        if (StringUtils.equals(year, "default")) {
            season = C2Season.getDefault();
        } else {
            season = C2Season.of(year);
        }
        Objects.requireNonNull(season, "season must be non null");

        SiteUser siteUser = siteUserService.findSiteUserByUsername(username);
        if (siteUser.getC2User() == null) {
            log.warn("site user is not connected with c2 user, no season summary. site user: {}", siteUser);
            return CommonResult.failed("no connected c2 user found");
        }

        return CommonResult.success(
                new WorkoutStatsDTO(workoutSummaryService.getWorkoutStatsBySiteUser(siteUser, season))
        );
    }

}
