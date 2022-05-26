package io.github.firstblood1985.c2visualizer.controller;

import io.github.firstblood1985.c2visualizer.common.api.CommonResult;
import io.github.firstblood1985.c2visualizer.common.api.ResultCode;
import io.github.firstblood1985.c2visualizer.common.util.Utils;
import io.github.firstblood1985.c2visualizer.domain.user.C2User;
import io.github.firstblood1985.c2visualizer.domain.user.SiteUser;
import io.github.firstblood1985.c2visualizer.domain.user.dto.C2UserParam;
import io.github.firstblood1985.c2visualizer.service.user.C2UserService;
import io.github.firstblood1985.c2visualizer.service.user.SiteUserService;
import io.github.firstblood1985.c2visualizer.service.user.c2authenticate.C2AuthenticateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * usage of this class: C2UserController
 * created by limin @ 2022/5/2
 */
@Api(tags = "this c2 user controller")
@RestController
@RequestMapping("/api")
@Slf4j
public class C2UserController {
    @Autowired
    SiteUserService siteUserService;

    @Autowired
    C2UserService c2UserService;

    @ApiOperation("check if has c2 user connected")
    @RequestMapping(value = "/isConnectedWithC2User", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult isConnectedWithC2User() {
        String username = Utils.getCurrentSiteUsername();
        if (!Utils.checkUsername(log, username))
            return CommonResult.failed(ResultCode.VALIDATE_FAILED);

        SiteUser siteUser = siteUserService.findSiteUserByUsername(username);
        log.info("found C2 User: {}", siteUser.getC2User());

        return CommonResult.success(siteUser.getC2User());
    }

    @ApiOperation("connect with C2 user")
    @RequestMapping(value = "/connectWithC2User", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult connectWithC2User(@NonNull @RequestBody C2UserParam c2UserParam) {
        String username = Utils.getCurrentSiteUsername();
        if (!Utils.checkUsername(log, username))
            return CommonResult.failed(ResultCode.VALIDATE_FAILED);

        SiteUser siteUser = siteUserService.findSiteUserByUsername(username);
        log.info("found site user: {}", siteUser);
        /***
         * if (siteUser already connect with c2 user && username is the same)
         * {
         *      authenticate user
         *      update c2user profile
         *      return
         *  }
         *     authenticate with c2user param
         *     get profile
         *     create new c2user
         *     connect with c2user with site user
         *      return
         */
        C2User c2User = siteUser.getC2User();
        if (c2User != null && StringUtils.equals(c2User.getUsername(), c2UserParam.getUsername())) {
            //authenticate and update profile
            log.info("site user is connected with c2 user: {}, updating profile", c2User);
            if (c2UserService.authenticateC2User(c2UserParam)) {
                c2UserService.updateC2User(c2User, c2UserParam);
                return CommonResult.success(siteUser);
            } else {

                return CommonResult.failed(ResultCode.VALIDATE_FAILED, "failed to login with C2UserParam: " + c2UserParam);
            }
        }
        log.info("site user is not connected with c2 user, authenticate and create new one with c2UserParam: {}", c2UserParam);
        c2User = c2UserService.createC2User(c2UserParam);
        if (c2User == null) {
            return CommonResult.failed(ResultCode.VALIDATE_FAILED, "failed to login with C2UserParam: " + c2UserParam);
        }
        siteUserService.connectSiteUserWithC2User(siteUser, c2User);
        return CommonResult.success(siteUser);

    }
}
