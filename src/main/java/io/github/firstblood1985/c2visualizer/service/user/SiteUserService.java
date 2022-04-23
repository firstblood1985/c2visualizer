package io.github.firstblood1985.c2visualizer.service.user;

import io.github.firstblood1985.c2visualizer.domain.user.C2User;
import io.github.firstblood1985.c2visualizer.domain.user.SiteUser;
import io.github.firstblood1985.c2visualizer.domain.user.WXUser;
import io.github.firstblood1985.c2visualizer.domain.user.dto.SiteUserParam;

/**
 * usage of this interface: SiteUserService
 * created by limin @ 2022/4/22
 */
public interface SiteUserService {
    SiteUser createSiteUser(SiteUserParam siteUserParam);

    SiteUser connectSiteUserWithWXUser(SiteUser siteUser, WXUser wxUser);

    SiteUser connectSiteUserWithC2User(SiteUser siteUser, C2User c2User);
}
