package io.github.firstblood1985.c2visualizer.domain.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * usage of this class: WXUser
 * created by limin @ 2022/4/18
 */
@Getter
@Setter
@Entity
public class WXUser extends User {


    private String openId;

    private String phoneNumber;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

}
