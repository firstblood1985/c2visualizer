package io.github.firstblood1985.c2visualizer.service.user.impl;

import io.github.firstblood1985.c2visualizer.dao.C2UserRepository;
import io.github.firstblood1985.c2visualizer.domain.user.C2User;
import io.github.firstblood1985.c2visualizer.domain.user.dto.C2UserParam;
import io.github.firstblood1985.c2visualizer.service.user.C2UserService;
import io.github.firstblood1985.c2visualizer.service.user.c2authenticate.C2AuthenticateService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * usage of this class: C2UserServiceImpl
 * created by limin @ 2022/4/22
 */
@Slf4j
@Service
public class C2UserServiceImpl implements C2UserService {
    @Autowired
    private C2AuthenticateService c2AuthenticateService;

    @Autowired
    private C2UserRepository c2UserRepository;

    @Override
    public C2User createC2User(@NonNull C2UserParam c2UserParam) {
        C2User c2User = c2UserRepository.findByUsernameIgnoreCase(c2UserParam.getUsername());

        if (c2User == null)
            c2User = new C2User();

        if (c2AuthenticateService.authenticate(c2UserParam)) {
            BeanUtils.copyProperties(c2UserParam, c2User);
            if (c2UserParam.getLogbookId() == null)
                c2User.setSynced(false);
            else
                c2User.setSynced(true);
            return c2UserRepository.save(c2User);
        } else {
            log.warn("Failed to create C2User due to authentication failure, C2UserParam: {}", c2UserParam);
            return null;
        }
    }

    @Override
    public C2User queryC2User(C2UserParam c2UserParam) {
        return null;
    }

    @Override
    public Boolean authenticateC2User(@NonNull C2UserParam c2UserParam) {

        return c2AuthenticateService.authenticate(c2UserParam);
    }

    @Override
    public C2User updateC2User(@NonNull C2User c2User, @NonNull C2UserParam c2UserParam) {
        BeanUtils.copyProperties(c2UserParam, c2User);

        return c2UserRepository.save(c2User);
    }

}
