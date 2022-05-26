package io.github.firstblood1985.c2visualizer.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * usage of this class: WXLoginRequest
 * created by limin @ 2022/4/30
 */
@Data
public class WXLoginRequest {
    private String code;

    private String grantType;
}
