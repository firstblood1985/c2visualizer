package io.github.firstblood1985.c2visualizer.domain.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * usage of this class: SiteUser
 * created by limin @ 2022/4/18
 */
@Getter
@Setter
@Entity
public class SiteUser extends User {

    @OneToOne
    @JoinColumn(name = "c_2_user_ID")
    private C2User c2User;

    @OneToOne
    @JoinColumn(name = "wx_user_ID")
    private WXUser wxUser;

    private String userName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Override
    public String getPrefix() {
        return "SiteUser";
    }
}
