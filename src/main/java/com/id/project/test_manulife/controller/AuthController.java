package com.id.project.test_manulife.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.id.project.test_manulife.model.dto.token.TokenRequestDto;
import com.id.project.test_manulife.model.dto.token.TokenResponseDto;
import com.id.project.test_manulife.service.AuthService;
import com.id.project.test_manulife.util.LoggingUtil;
import jakarta.validation.Valid;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    private AuthService authService;

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/token")
    public ResponseEntity<TokenResponseDto> getToken(@Valid @RequestBody TokenRequestDto request) throws JsonProcessingException {
        DateTime startTime = DateTime.now();
        String apiPath = "/oauth/token";

        LoggingUtil.logRequestFE(apiPath, objectMapper.writeValueAsString(request));

        TokenResponseDto response = authService.generateToken(request);

        LoggingUtil.logResponseFE(apiPath, objectMapper.writeValueAsString(response), startTime);

        return ResponseEntity.ok(response);
    }
}