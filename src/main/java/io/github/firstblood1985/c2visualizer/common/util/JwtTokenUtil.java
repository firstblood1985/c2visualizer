package io.github.firstblood1985.c2visualizer.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * usage of this class: JwtTokenUtil
 * created by limin @ 2022/2/22
 */
@Component
public class JwtTokenUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    public static final String CLAIM_KEY_USERNAME = "sub";
    public static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    private String generateJwtToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims)
                .setExpiration(generateExpiration())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Date generateExpiration() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.info("JWT token 验证失败: {}", token);
        }
        return claims;
    }

    public String getUserNameFromToken(String token) {
        String userName = null;
        try {
            Claims claims = getClaimsFromToken(token);
            userName = claims.getSubject();
        } catch (Exception e) {
            LOGGER.info("JWT token 无法获取用户名: {}", token);
        }
        return userName;
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String userName = getUserNameFromToken(token);

        if (userName != null && userName.equals(userDetails.getUsername()) && !isTokenExpired(token)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date exprireDate = getExpiredDateFromToken(token);
        if (null != exprireDate)
            return exprireDate.before(new Date());
        else//always expire the token if we cannot validate it
            return true;
    }

    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        if (null != claims)
            return claims.getExpiration();

        return null;
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(userDetails.getUsername());
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, username);
        claims.put(CLAIM_KEY_CREATED, new Date());

        return generateJwtToken(claims);
    }

    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    public String refrehToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateJwtToken(claims);
    }

}
