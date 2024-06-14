package com.example.whopper.domain.auth.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
public class RefreshTokenEntity {

    @Id
    private String id;

    @Indexed
    private String token;

    @TimeToLive
    private Long timeToLive;
}
