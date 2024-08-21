package com.example.whopper.common.security.jwt;

import com.example.whopper.domain.refreshtoken.RefreshTokenEntity;
import com.example.whopper.domain.refreshtoken.type.UserRole;
import com.example.whopper.interfaces.auth.dto.response.TokenResponse;
import com.example.whopper.common.exception.auth.ExpiredTokenException;
import com.example.whopper.common.exception.auth.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    private final UserDetailsService userDetailsService;

    private final RefreshTokenRepository refreshTokenRepository;

    // access token 생성
    private String createAccessToken(String id, UserRole userRole) {

        Date now = new Date();

        return Jwts.builder()
                .setSubject(id)
                .claim("type", "access")
                .claim("user", getSecret(userRole))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.accessExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.secret())
                .compact();
    }

    private String createRefreshToken(String id, UserRole userRole) {

        Date now = new Date();

        String refreshToken = Jwts.builder()
                .claim("type", "refresh")
                .claim("user", getSecret(userRole))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.refreshExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.secret())
                .compact();

        refreshTokenRepository.save(
                RefreshTokenEntity.builder()
                        .id(id)
                        .userRole(userRole)
                        .token(refreshToken)
                        .timeToLive(jwtProperties.refreshExpiration())
                        .build());

        return refreshToken;
    }

    private String getSecret(UserRole userRole) {
        if (userRole.equals(UserRole.TEACHER)) {
            return jwtProperties.teacherSecret();
        }

        return jwtProperties.studentSecret();
    }

    // 토큰에 담겨있는 userId로 SpringSecurity Authentication 정보를 반환하는 메서드
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);

        UserDetails userDetails = userDetailsService.loadUserByUsername(
                claims.getSubject() + ":" + claims.get("user")
        );

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Claims getClaims(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(jwtProperties.secret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    public TokenResponse receiveToken(String id, UserRole userRole) {

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        return TokenResponse
                .builder()
                .accessToken(createAccessToken(id, userRole))
                .refreshToken(createRefreshToken(id, userRole))
                .accessExpiredAt(now.plusSeconds(jwtProperties.accessExpiration()).toEpochSecond())
                .refreshExpiredAt(now.plusSeconds(jwtProperties.refreshExpiration()).toEpochSecond())
                .build();
    }

    // HTTP 요청 헤더에서 토큰을 가져오는 메서드
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtProperties.header());

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtProperties.prefix())
                && bearerToken.length() > jwtProperties.prefix().length() + 1) {
            return bearerToken.substring(7);
        }
        return null;
    }
}