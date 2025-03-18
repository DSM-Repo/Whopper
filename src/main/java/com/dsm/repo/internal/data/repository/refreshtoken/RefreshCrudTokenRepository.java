package com.dsm.repo.internal.data.repository.refreshtoken;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshCrudTokenRepository extends CrudRepository<RefreshTokenEntity, String> {
    Optional<RefreshTokenEntity> findByToken(String token);
}
