package io.github.firstblood1985.c2visualizer.dao;

import io.github.firstblood1985.c2visualizer.domain.user.SiteUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface SiteUserRepository extends CrudRepository<SiteUser, Long> {
    Optional<SiteUser> findByUserName(String userName);

    long countByIdNotNull();

}