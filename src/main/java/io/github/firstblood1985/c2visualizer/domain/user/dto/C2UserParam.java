package io.github.firstblood1985.c2visualizer.domain.user.dto;

import io.github.firstblood1985.c2visualizer.domain.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

/**
 * usage of this class: C2UserDTO
 * created by limin @ 2022/4/21
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class C2UserParam extends BaseModel {
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

    public void initFromMapProfile(Map<String, String> mapProfile) {
        Field[] fields = C2UserParam.class.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            String v = mapProfile.getOrDefault(field.getName(),null);
            try {
                if(field.get(this)==null)
                    field.set(this,v);
            } catch (IllegalAccessException e) {
            }
        });
    }

}
