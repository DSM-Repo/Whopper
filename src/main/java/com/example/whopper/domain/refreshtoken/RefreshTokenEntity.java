package com.example.whopper.domain.refreshtoken;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class RefreshTokenEntity {
    @Id
    private String id;
    private UserRole role;

    @Indexed
    private String token;

    @TimeToLive
    private Long timeToLive;

    enum UserRole {
        STUDENT,
        TEACHER
    }
}
