package org.example.repository.interfaces;

import org.example.models.Token;
import org.example.models.User;

import java.util.List;

public interface TokenRepository {

    Token save(Token token);

    Token findByUserId(Long userId);

    void update(Token token);

    void delete(Long id);

    List<Token> findTokenByUserId(Long userId);
}
