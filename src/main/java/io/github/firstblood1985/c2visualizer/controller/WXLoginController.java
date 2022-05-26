package io.github.firstblood1985.c2visualizer.controller;

import io.github.firstblood1985.c2visualizer.common.api.CommonResult;
import io.github.firstblood1985.c2visualizer.domain.user.WXUser;
import io.github.firstblood1985.c2visualizer.domain.user.dto.WXUserParam;
import io.github.firstblood1985.c2visualizer.service.user.SiteUserService;
import io.github.firstblood1985.c2visualizer.service.user.WXUserService;
import io.github.firstblood1985.c2visualizer.service.wx.WXLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * usage of this class: WXLoginController
 * created by limin @ 2022/4/30
 */
@Api(tags = "this wx login controller")
@RestController
@RequestMapping("/api")
@Slf4j
public class WXLoginController {

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    WXLoginService wxLoginService;

    @Autowired
    SiteUserService siteUserService;

    @Autowired
    WXUserService wxUserService;

    @ApiOperation("login from wx mini app")
    @RequestMapping(value = "/wxLogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody WXLoginRequest request) {
        if(null == request)
        {
            log.warn("null wx login request received");
            return null;
        }
        log.info(request.toString());

        String openId = wxLoginService.login(request.getCode(),request.getGrantType());

        return loginWithOpenId(openId);
    }

    @ApiOperation("login with openId")
    @RequestMapping(value = "/wxLoginWithOpenId", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult loginWithOpenId(@RequestBody String openId) {

        WXUserParam wxUserParam = WXUserParam.builder().openId(openId).build();

        WXUser wxUser = wxUserService.findWXUserByOpenId(openId);
        if(null == wxUser) {
            wxUser = wxUserService.createWXUser(wxUserParam);
        }

        String token = siteUserService.loginWithWXUser(wxUser);

        Map<String,String> results = new HashMap<>();
        results.put("token",token);
        results.put("tokenHead",tokenHead);

        return CommonResult.success(results);
    }



}
