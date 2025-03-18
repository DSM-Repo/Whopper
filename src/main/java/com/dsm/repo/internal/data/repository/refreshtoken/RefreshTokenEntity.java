package com.dsm.repo.internal.data.repository.refreshtoken;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshTokenEntity {
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

    protected RefreshTokenEntity() {}
}
