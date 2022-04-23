package io.github.firstblood1985.c2visualizer.service.user.impl;

import io.github.firstblood1985.c2visualizer.dao.WXUserRepository;
import io.github.firstblood1985.c2visualizer.domain.user.WXUser;
import io.github.firstblood1985.c2visualizer.domain.user.dto.WXUserParam;
import io.github.firstblood1985.c2visualizer.service.user.WXUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * usage of this class: WXUserServiceImpl
 * created by limin @ 2022/4/22
 */
public class WXUserServiceImpl implements WXUserService {
    @Autowired
    WXUserRepository wxUserRepository;

    @Override
    public WXUser createWXUser(WXUserParam wxUserParam) {
        WXUser wxUser = new WXUser();
        BeanUtils.copyProperties(wxUserParam,wxUser);
        return wxUserRepository.save(wxUser);
    }
}
