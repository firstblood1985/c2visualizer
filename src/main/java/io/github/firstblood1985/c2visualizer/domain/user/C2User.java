package io.github.firstblood1985.c2visualizer.domain.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * usage of this class: C2User
 * created by limin @ 2022/4/18
 */
@Getter
@Setter
@Entity
public class C2User extends User {


    private String logbookId;

    private String fullName;

    private String age;

    private String location;

    private String affiliation;

    private String team;

    private String email;

    private String height;

    private String weight;

    private String memberSince;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


}
