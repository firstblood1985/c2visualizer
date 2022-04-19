package io.github.firstblood1985.c2visualizer.domain.user;

import io.github.firstblood1985.c2visualizer.domain.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * usage of this class: SiteUser
 * created by limin @ 2022/4/18
 */
@Getter
@Setter
@MappedSuperclass
public abstract class User extends BaseModel {

    @CreationTimestamp
    @Column(name = "create_time", columnDefinition = "TIMESTAMP")
    public LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "TIMESTAMP")
    public LocalDateTime updateTime;
}
