package com.id.project.test_manulife.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.id.project.test_manulife.model.dto.common.BaseResponseDto;
import com.id.project.test_manulife.model.dto.dukcapil.ClientDukcapilResponseDto;
import com.id.project.test_manulife.service.ClientDukcapilService;
import com.id.project.test_manulife.util.LoggingUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client-dukcapil")
public class ClientDukcapilController {

    @Autowired
    private ClientDukcapilService clientDukcapilService;

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @GetMapping(
            value = "/details/{policyNumber}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponseDto<List<ClientDukcapilResponseDto>>> getClientDukcapil(
            @PathVariable Long policyNumber,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestHeader(value = HttpHeaders.CONTENT_TYPE, required = false, defaultValue = MediaType.APPLICATION_JSON_VALUE) String contentType,
            @RequestHeader(value = HttpHeaders.ACCEPT, required = false, defaultValue = MediaType.APPLICATION_JSON_VALUE) String accept
    ) throws JsonProcessingException {

        DateTime startTime = DateTime.now();
        String apiPath = "/api/v1/client-dukcapil/details/" + policyNumber;

        String requestLogInfo = String.format("Params [policyNumber: %d] | Headers [Authorization: %s, Content-Type: %s, Accept: %s]",
                policyNumber, authHeader, contentType, accept);
        LoggingUtil.logRequestFE(apiPath, requestLogInfo);

        BaseResponseDto<List<ClientDukcapilResponseDto>> response =
                clientDukcapilService.getGroupedClientDukcapilByPolicyNumber(policyNumber);

        LoggingUtil.logResponseFE(apiPath, objectMapper.writeValueAsString(response), startTime);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(response);
    }
}