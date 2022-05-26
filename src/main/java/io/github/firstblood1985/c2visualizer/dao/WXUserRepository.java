package io.github.firstblood1985.c2visualizer.dao;

import io.github.firstblood1985.c2visualizer.domain.user.WXUser;
import org.springframework.data.repository.CrudRepository;

/**
 * usage of this interface: WXUserRepository
 * created by limin @ 2022/4/22
 */
public interface WXUserRepository extends CrudRepository<WXUser,Long> {
    WXUser findByOpenId(String openId);
}
