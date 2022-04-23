package io.github.firstblood1985.c2visualizer.service.user;

import io.github.firstblood1985.c2visualizer.domain.user.C2User;
import io.github.firstblood1985.c2visualizer.domain.user.dto.C2UserParam;

/**
 * usage of this interface: C2UserService
 * created by limin @ 2022/4/21
 */
public interface C2UserService {

    C2User createC2User(C2UserParam c2UserParam);

    C2User queryC2User(C2UserParam c2UserParam);

    /**
     * for test connection with C2 site
     * */
    Boolean authenticateC2User(C2UserParam c2UserParam);
}
