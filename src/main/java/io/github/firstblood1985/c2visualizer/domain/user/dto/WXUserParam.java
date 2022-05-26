package io.github.firstblood1985.c2visualizer.domain.user.dto;

import lombok.Builder;
import lombok.Data;

/**
 * usage of this class: WXUserParam
 * created by limin @ 2022/4/22
 */
@Data
@Builder
public class WXUserParam {

    private String openId;
}
