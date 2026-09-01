package com.id.project.test_manulife.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.id.project.test_manulife.dto.token.TokenRequest;
import com.id.project.test_manulife.dto.token.TokenResponse;
import com.id.project.test_manulife.generator.TokenGenerator;
import com.id.project.test_manulife.util.LoggingUtil;
import com.id.project.test_manulife.validator.AuthValidator;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthValidator authValidator;

    @Autowired
    private TokenGenerator tokenGenerator;

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public TokenResponse generateToken(TokenRequest request) throws JsonProcessingException {
        DateTime startTime = DateTime.now();
        String flow = "OAUTH_TOKEN_GENERATION";

        LoggingUtil.logRequest(flow, objectMapper.writeValueAsString(request));

        // 1. Validasi credentials
        authValidator.validateCredentials(request);

        // 2. Generate token
        String generatedToken = tokenGenerator.generateBearerToken();

        // 3. Build response
        TokenResponse response = TokenResponse.builder()
                .accessToken(generatedToken)
                .expiresIn(3599)
                .tokenType("bearer")
                .build();

        LoggingUtil.logResponse(flow, objectMapper.writeValueAsString(response), startTime);

        return response;
    }
}