package com.id.project.test_manulife.validator;

import com.id.project.test_manulife.model.dto.token.TokenRequestDto;
import com.id.project.test_manulife.exception.InvalidClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthValidator {

    @Value("${oauth.client.id}")
    private String oauthClientId;

    @Value("${oauth.client.secret}")
    private String oauthClientSecret;

    public void validateCredentials(TokenRequestDto request) {
        boolean isValidClient = oauthClientId.equals(request.getClientId());
        boolean isValidSecret = oauthClientSecret.equals(request.getClientSecret());
        boolean isValidGrant = "client_credentials".equals(request.getGrantType());

        if (!isValidClient || !isValidSecret || !isValidGrant) {
            throw new InvalidClientException("Client authentication failed");
        }
    }
}
