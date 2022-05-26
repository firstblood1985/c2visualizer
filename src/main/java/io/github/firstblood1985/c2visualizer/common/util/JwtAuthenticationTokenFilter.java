package io.github.firstblood1985.c2visualizer.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * usage of this class: 在用户名和密码校验前添加的过滤器，如果请求中有jwt的token且有效，
 * 会取出token中的用户名，然后调用SpringSecurity的API进行登录操作。
 * created by limin @ 2022/2/28
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenhead}")
    private String tokenHead;

    @Value("${jwt.tokenheader}")
    private String tokenheader;

    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHead = request.getHeader(this.tokenheader);
        //jwt token is used
        if (null != authHead && authHead.startsWith(this.tokenHead)) {
            String authToken = authHead.substring(this.tokenHead.length());
            String userName = jwtTokenUtil.getUserNameFromToken(authToken);
            if (!StringUtils.isEmpty(userName)) {
                LOGGER.info("check username: {}", userName);
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

                if (null != userDetails && jwtTokenUtil.validateToken(authToken, userDetails)) { // build and set authentication into spring context
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    LOGGER.info("authenticated user: {}", userName);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}

















