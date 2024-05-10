package com.example.whopper.domain.auth.dao;

import com.example.whopper.domain.auth.domain.RefreshTokenEntity;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshTokenEntity, String> {
}
