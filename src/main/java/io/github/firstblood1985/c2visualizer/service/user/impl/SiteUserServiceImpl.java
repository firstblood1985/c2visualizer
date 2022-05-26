package io.github.firstblood1985.c2visualizer.service.user.impl;

import io.github.firstblood1985.c2visualizer.common.util.JwtTokenUtil;
import io.github.firstblood1985.c2visualizer.dao.SiteUserRepository;
import io.github.firstblood1985.c2visualizer.domain.user.C2User;
import io.github.firstblood1985.c2visualizer.domain.user.SiteUser;
import io.github.firstblood1985.c2visualizer.domain.user.WXUser;
import io.github.firstblood1985.c2visualizer.domain.user.dto.SiteUserParam;
import io.github.firstblood1985.c2visualizer.service.user.SiteUserService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * usage of this class: SiteUserServiceImpl
 * created by limin @ 2022/4/22
 */
@Service
@Slf4j
public class SiteUserServiceImpl implements SiteUserService {
    @Autowired
    private SiteUserRepository siteUserRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public SiteUser createSiteUser(SiteUserParam siteUserParam) {
        log.info("create site user: {}", siteUserParam);

        return saveSiteUser( (siteUserParam1) -> initSiteUser(siteUserParam1),siteUserParam);
    }

    @Override
    public SiteUser connectSiteUserWithWXUser(SiteUser siteUser, WXUser wxUser) {
        siteUser.setWxUser(wxUser);
        return siteUserRepository.save(siteUser);
    }

    @Override
    public SiteUser connectSiteUserWithC2User(SiteUser siteUser, C2User c2User) {
        siteUser.setC2User(c2User);
        return siteUserRepository.save(siteUser);
    }

    @Override
    public SiteUser findSiteUserByUsername(@NonNull String username) {
        return siteUserRepository.findByUserName(username);
    }

    @Override
    public SiteUser findSiteUserByWXUser(WXUser wxUser) {
        return null;
    }

    @Override
    public String loginWithWXUser(WXUser wxUser) {
        log.info("log in as wx user: {}",wxUser);
        String username = wxUser.getPrefix()+":"+wxUser.getOpenId();
        SiteUser siteUser = findSiteUserByUsername(username);

        if(null == siteUser)
        {
            log.info("first time log in as wx user: {}", wxUser);
            SiteUserParam siteUserParam = SiteUserParam.builder()
                    .wxUser(wxUser)
                    .userName(SiteUserParam.usernameFrom(wxUser,wxUser.getOpenId()))
                    .build();
            siteUser = createSiteUser(siteUserParam);
        }

        String token = jwtTokenUtil.generateToken(username);
        return token;
    }

    private SiteUser saveSiteUser(Function<SiteUserParam, SiteUser> initSiteUser,SiteUserParam p) {
        return siteUserRepository.save(initSiteUser.apply(p));
    }

    private SiteUser initSiteUser(SiteUserParam siteUserParam) {
        SiteUser siteUser = new SiteUser();
        BeanUtils.copyProperties(siteUserParam, siteUser);
        return siteUser;
    }
}
