package io.github.firstblood1985.c2visualizer.repository;

import io.github.firstblood1985.c2visualizer.dao.SiteUserRepository;
import io.github.firstblood1985.c2visualizer.domain.user.C2User;
import io.github.firstblood1985.c2visualizer.domain.user.SiteUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.Optional;

/**
 * usage of this class: SiteUserRepositoryTest
 * created by limin @ 2022/4/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SiteUserRepositoryIntegrationTest {

    @Autowired
    SiteUserRepository siteUserRepository;

    @Autowired
    DataSource dataSource;

    @Test
    public void testUserRepositoryNotNull() {
        Assert.assertNotNull(siteUserRepository);
    }

    @Test
    public void testFindByUserName() {
//        long count = siteUserRepository.countByIdNotNull();
//        Assert.assertEquals(1,count);
//
//        Optional<SiteUser> siteUser = siteUserRepository.findByUserName("firstblood1985");
//
//        Assert.assertEquals("firstblood1985",siteUser.get().getUserName());
//
//        C2User c2User = siteUser.get().getC2User();
//        Assert.assertEquals("Min Li",c2User.getFullName());
//        Assert.assertNotNull(c2User.getCreateTime());

    }

}
