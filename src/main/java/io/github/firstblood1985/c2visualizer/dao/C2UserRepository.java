package io.github.firstblood1985.c2visualizer.dao;

import io.github.firstblood1985.c2visualizer.domain.user.C2User;
import org.springframework.data.repository.CrudRepository;

/**
 * usage of this interface: C2UserRepository
 * created by limin @ 2022/4/22
 */
public interface C2UserRepository extends CrudRepository<C2User,Long> {
    C2User findByUsernameIgnoreCase(String username);

}
