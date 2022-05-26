package io.github.firstblood1985.c2visualizer.domain.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * usage of this class: C2User
 * created by limin @ 2022/4/18
 */
@Getter
@Setter
@Entity
public class C2User extends User {

    private static final long serialVersionUID = -8106244235391674064L;
    private String logbookId;

    private String username;

    private String password;

    private String fullName;

    private String age;

    private String location;

    private String affiliation;

    private String team;

    private String email;

    private String height;

    private String weight;

    private String memberSince;

    private Boolean synced;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Override
    public String getPrefix() {
        return "C2User";
    }
}
