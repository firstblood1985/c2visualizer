package io.github.firstblood1985.c2visualizer.service.user;

import io.github.firstblood1985.c2visualizer.domain.user.WXUser;
import io.github.firstblood1985.c2visualizer.domain.user.dto.WXUserParam;

/**
 * usage of this interface: WXUserService
 * created by limin @ 2022/4/22
 */
public interface WXUserService {
    WXUser createWXUser(WXUserParam wxUserParam);

}
