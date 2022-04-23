package io.github.firstblood1985.c2visualizer.service.user.impl;

import io.github.firstblood1985.c2visualizer.dao.SiteUserRepository;
import io.github.firstblood1985.c2visualizer.domain.user.C2User;
import io.github.firstblood1985.c2visualizer.domain.user.SiteUser;
import io.github.firstblood1985.c2visualizer.domain.user.WXUser;
import io.github.firstblood1985.c2visualizer.domain.user.dto.SiteUserParam;
import io.github.firstblood1985.c2visualizer.service.user.SiteUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * usage of this class: SiteUserServiceImpl
 * created by limin @ 2022/4/22
 */
@Service
public class SiteUserServiceImpl implements SiteUserService {
    @Autowired
    private SiteUserRepository siteUserRepository;

    @Override
    public SiteUser createSiteUser(SiteUserParam siteUserParam) {

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

    private SiteUser saveSiteUser(Function<SiteUserParam, SiteUser> initSiteUser,SiteUserParam p) {
        return siteUserRepository.save(initSiteUser.apply(p));
    }

    private SiteUser initSiteUser(SiteUserParam siteUserParam) {
        SiteUser siteUser = new SiteUser();
        BeanUtils.copyProperties(siteUserParam, siteUser);
        return siteUser;
    }
}
