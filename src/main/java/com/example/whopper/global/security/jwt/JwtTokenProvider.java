package com.example.whopper.global.security.jwt;

import com.example.whopper.domain.auth.dao.RefreshTokenRepository;
import com.example.whopper.domain.auth.domain.RefreshTokenEntity;
import com.example.whopper.domain.auth.dto.response.TokenResponse;
import com.example.whopper.domain.auth.exception.ExpiredTokenException;
import com.example.whopper.domain.auth.exception.InvalidTokenException;
import com.example.whopper.global.security.auth.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    private final CustomUserDetailsService customUserDetailsService;

    private final RefreshTokenRepository refreshTokenRepository;

    // access token 생성
    public String createAccessToken(String accountId) {

        Date now = new Date();

        return Jwts.builder()
                .setSubject(accountId)
                .claim("type", "access")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.accessExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.secret())
                .compact();
    }

    public String createRefreshToken(String accountId) {

        Date now = new Date();

        String refreshToken = Jwts.builder()
                .claim("type", "refresh")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.refreshExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.secret())
                .compact();

        refreshTokenRepository.save(
                RefreshTokenEntity.builder()
                        .id(accountId)
                        .token(refreshToken)
                        .timeToLive(jwtProperties.refreshExpiration())
                        .build());

        return refreshToken;
    }

    // 토큰에 담겨있는 userId로 SpringSecurity Authentication 정보를 반환하는 메서드
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(claims.getSubject());
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

    public TokenResponse receiveToken(String accountId) {

        Date now = new Date();

        return TokenResponse
                .builder()
                .accessToken(createAccessToken(accountId))
                .refreshToken(createRefreshToken(accountId))
                .accessExpiredAt(new Date(now.getTime() + jwtProperties.getAccessExpiration()))
                .refreshExpiredAt(new Date(now.getTime() + jwtProperties.getRefreshExpiration()))
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