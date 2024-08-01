package com.mbti_j.myroutine.backend.security.jwt;

import com.mbti_j.myroutine.backend.model.dto.user.LoginUserInfoDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtUtil {

    private final Key key;
    private final long accessTokenExpTime;
    private final Set<String> blacklist = Collections.synchronizedSet(new HashSet<>());

    public JwtUtil(
            @Value("${jwt.secret}") String secretKey,
            @Value(("${jwt.access-token.expire-time}")) long accessTokenExpTime
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpTime = accessTokenExpTime;
    }

    public String createAccessToken(LoginUserInfoDto loginUserInfoDto) {
        log.info("JwtUtil createAccessToken 실행!@!@!@!@!@!@");
        return createToken(loginUserInfoDto, accessTokenExpTime);
    }

    private String createToken(LoginUserInfoDto user, long accessTokenExpTime) {
        Claims claims = Jwts.claims();
        claims.put("userId", user.getId());
        claims.put("nickname", user.getNickname());
        claims.put("email", user.getEmail());
        log.info(claims.toString());
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(accessTokenExpTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Long getUserId(String token) {
        return parseClaims(token).get("userId", Long.class);
    }

    /*
     * JWT 검증
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            // JWT 서명 or JWT 구조 예외
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            // JWT 만료시
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            // JWT 형식 지원 안될떄
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            // JWT 클레임 문자열이 비어있을때
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    /*
     * JWT Claims 추출
     */
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}
