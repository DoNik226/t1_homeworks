package ru.nikita.t1.repository;

import ru.nikita.t1.model.SystemUserDetails;
import ru.nikita.t1.model.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    Optional<TokenEntity> findTopByUserDetailsOrderByExpirationDateDesc(SystemUserDetails userDetails);
}
