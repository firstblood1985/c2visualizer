package io.github.firstblood1985.c2visualizer.domain.user.dto;

import io.github.firstblood1985.c2visualizer.domain.user.C2User;
import io.github.firstblood1985.c2visualizer.domain.user.User;
import io.github.firstblood1985.c2visualizer.domain.user.WXUser;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * usage of this class: SiteUserParam
 * created by limin @ 2022/4/22
 */
@Getter
@Setter
@Builder
@ToString
public class SiteUserParam {
    private String userName;

    private C2User c2User;

    private WXUser wxUser;

    public static String usernameFrom(User user, String username)
    {
        return user.getPrefix()+":"+username;
    }
}
