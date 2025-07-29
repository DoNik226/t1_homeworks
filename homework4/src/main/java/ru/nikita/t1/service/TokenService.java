package ru.nikita.t1.service;

import ru.nikita.t1.model.SystemUserDetails;
import ru.nikita.t1.model.TokenEntity;
import ru.nikita.t1.repository.TokenRepository;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(@Nonnull TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Transactional
    public void saveToken(TokenEntity tokenEntity) {
        tokenRepository.save(tokenEntity);
    }

    @Transactional
    public Optional<TokenEntity> getLastTokenForUser(SystemUserDetails user) {
        return tokenRepository.findTopByUserDetailsOrderByExpirationDateDesc(user);
    }
}
