package io.github.firstblood1985.c2visualizer.domain.workout;

import io.github.firstblood1985.c2visualizer.domain.BaseModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * usage of this class: WorkoutBaseModel
 * created by limin @ 2022/4/20
 */
@MappedSuperclass
public abstract class WorkoutBaseModel extends BaseModel {
    @CreationTimestamp
    @Column(name = "create_time", columnDefinition = "TIMESTAMP")
    protected LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "update_time", columnDefinition = "TIMESTAMP")
    protected LocalDateTime updateTime;
}
