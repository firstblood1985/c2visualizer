package io.github.firstblood1985.c2visualizer.service.user.impl;

import io.github.firstblood1985.c2visualizer.dao.C2UserRepository;
import io.github.firstblood1985.c2visualizer.domain.user.C2User;
import io.github.firstblood1985.c2visualizer.domain.user.dto.C2UserParam;
import io.github.firstblood1985.c2visualizer.service.user.C2UserService;
import io.github.firstblood1985.c2visualizer.service.user.c2authenticate.C2AuthenticateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * usage of this class: C2UserServiceImpl
 * created by limin @ 2022/4/22
 */
@Slf4j
public class C2UserServiceImpl implements C2UserService {
    @Autowired
    private C2AuthenticateService c2AuthenticateService;

    @Autowired
    private C2UserRepository c2UserRepository;

    @Override
    public C2User createC2User(C2UserParam c2UserParam) {
        if (c2AuthenticateService.authenticate(c2UserParam)) {
            C2User newC2User = new C2User();
            BeanUtils.copyProperties(c2UserParam,newC2User);
            newC2User.setSynced(true);
            return c2UserRepository.save(newC2User);
        } else {
            log.warn("Failed to create C2User due to authentication failure, C2UserParam: {}",c2UserParam);
            return null;
        }
    }

    @Override
    public C2User queryC2User(C2UserParam c2UserParam) {
        return null;
    }

    @Override
    public Boolean authenticateC2User(C2UserParam c2UserParam) {

        return c2AuthenticateService.authenticate(c2UserParam);
    }

}
