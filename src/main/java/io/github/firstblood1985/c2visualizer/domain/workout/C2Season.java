package io.github.firstblood1985.c2visualizer.domain.workout;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum C2Season {
    SEASON2023("2023"),
    SEASON2022("2022"),
    SEASON2021("2021");

    private String season;

    public LocalDate seasonStart() {
        return LocalDate.of(Integer.valueOf(season)-1,5,1);
    }

    public LocalDate seasonEnd(){
        return LocalDate.of(Integer.valueOf(season),4,30);
    }

    //default season is always the first one
    public static C2Season getDefault(){
        return C2Season.values()[0];
    }

    public static C2Season of(String year){
        Optional<C2Season> season = Arrays.stream(C2Season.values()).filter(s -> s.getSeason().equals(year)).findAny();
        return season.orElse(null);
    }




}
