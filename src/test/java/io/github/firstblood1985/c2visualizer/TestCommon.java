package io.github.firstblood1985.c2visualizer;

import io.github.firstblood1985.c2visualizer.domain.user.C2User;
import io.github.firstblood1985.c2visualizer.domain.user.SiteUser;
import io.github.firstblood1985.c2visualizer.domain.user.WXUser;
import io.github.firstblood1985.c2visualizer.domain.workout.WorkoutSummary;
import io.github.firstblood1985.c2visualizer.domain.workout.enums.WorkOutEnum;
import io.github.firstblood1985.c2visualizer.domain.workout.enums.WorkoutTypeEnum;
import org.springframework.beans.BeanUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * usage of this class: TestCommon
 * created by limin @ 2022/4/27
 */
public class TestCommon {

    public static List<WorkoutSummary> generateWorkoutSummaries() {
        WorkoutSummary workoutSummary = new WorkoutSummary();
        workoutSummary.setWorkoutDateTime(LocalDateTime.of(2022,4,21,20,30,0));
        workoutSummary.setWorkOut(WorkOutEnum.ROWERG);
        workoutSummary.setWorkoutType(WorkoutTypeEnum.FIXED_DISTANCE);
        workoutSummary.setCalories(615);
        workoutSummary.setMeters(10000);
        workoutSummary.setDuration(Duration.ofMinutes(55));
        workoutSummary.setLogbookId("1043029");
        workoutSummary.setLogId("63280804");
        workoutSummary.setPace("1:50.0");

        WorkoutSummary workoutSummary1 = new WorkoutSummary();
        BeanUtils.copyProperties(workoutSummary,workoutSummary1);
        workoutSummary1.setWorkoutDateTime(workoutSummary1.getWorkoutDateTime().plus(Duration.ofDays(1)));
        workoutSummary1.setLogId("63280805");
        workoutSummary1.setPace("1:59.9");
        workoutSummary1.setDuration(Duration.ofMinutes(40));

        WorkoutSummary workoutSummary2 = new WorkoutSummary();
        BeanUtils.copyProperties(workoutSummary,workoutSummary2);
        workoutSummary2.setWorkoutDateTime(workoutSummary1.getWorkoutDateTime().plus(Duration.ofDays(1)));
        workoutSummary2.setMeters(5000);
        workoutSummary2.setLogId("63280806");
        workoutSummary1.setPace("1:54.9");

        WorkoutSummary workoutSummary3 = new WorkoutSummary();
        BeanUtils.copyProperties(workoutSummary,workoutSummary3);
        workoutSummary3.setLogId("63280807");
        workoutSummary3.setMeters(1500);
        workoutSummary1.setPace("1:48.7");

        return Arrays.asList(workoutSummary,workoutSummary1,workoutSummary2,workoutSummary3);

    }

    public static C2User generateC2User() {
        C2User c2User = new C2User();
        c2User.setLogbookId("1043029");
        c2User.setUsername("firstblood1985");

        return c2User;
    }
    public static WXUser generateWXUser(){
        WXUser wxUser = new WXUser();
        wxUser.setOpenId("123456");

        return wxUser;
    }

    public static SiteUser generateSiteUser() {
        C2User c2User = generateC2User();

        WXUser wxUser = generateWXUser();

        SiteUser siteUser = new SiteUser();
        siteUser.setUserName("limin");
        siteUser.setC2User(c2User);
        siteUser.setWxUser(wxUser);

        return siteUser;
    }

}
