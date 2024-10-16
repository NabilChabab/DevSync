package org.example.repository.interfaces;

import org.example.models.TokenRequest;
import org.example.models.enums.Request;

public interface TokenRequestRepository {

    TokenRequest save(TokenRequest tokenRequest);

    void updateStatus(Long id, Request status);

}
