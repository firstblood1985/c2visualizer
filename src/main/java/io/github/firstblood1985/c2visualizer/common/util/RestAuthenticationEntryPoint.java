package io.github.firstblood1985.c2visualizer.common.util;

import cn.hutool.json.JSONUtil;
import io.github.firstblood1985.c2visualizer.common.api.CommonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * usage of this class: 当未登陆或token失效时，自定义返回结果
 * created by limin @ 2022/2/25
 */
@Component
public class RestAuthenticationEntryPoint
        implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        httpServletResponse.setCharacterEncoding(Constants.UTF8);
        httpServletResponse.setContentType(Constants.applicationJson);
        httpServletResponse.getWriter().println(JSONUtil.parse(CommonResult.unauthorized(e.getMessage())));
        httpServletResponse.getWriter().flush();
    }
}
