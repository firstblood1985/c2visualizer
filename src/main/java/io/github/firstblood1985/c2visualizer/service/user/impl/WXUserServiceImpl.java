package io.github.firstblood1985.c2visualizer.service.user.impl;

import io.github.firstblood1985.c2visualizer.dao.WXUserRepository;
import io.github.firstblood1985.c2visualizer.domain.user.SiteUser;
import io.github.firstblood1985.c2visualizer.domain.user.WXUser;
import io.github.firstblood1985.c2visualizer.domain.user.dto.WXUserParam;
import io.github.firstblood1985.c2visualizer.service.user.WXUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * usage of this class: WXUserServiceImpl
 * created by limin @ 2022/4/22
 */
@Service
@Slf4j
public class WXUserServiceImpl implements WXUserService {
    @Autowired
    WXUserRepository wxUserRepository;

    @Override
    public WXUser createWXUser(WXUserParam wxUserParam) {
        WXUser wxUser = new WXUser();
        BeanUtils.copyProperties(wxUserParam,wxUser);
        log.info("create wxUser: {}",wxUserParam);
        return wxUserRepository.save(wxUser);
    }

    @Override
    public WXUser findWXUserByOpenId(String openId) {
        log.info("find wx user with openid: {}", openId);
        return wxUserRepository.findByOpenId(openId);
    }

}
