package com.id.project.test_manulife.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.id.project.test_manulife.model.raw.dukcapil.ClientDukcapilRaw;
import com.id.project.test_manulife.model.dto.dukcapil.ClientDukcapilResponseDto;
import com.id.project.test_manulife.model.dto.common.BaseResponseDto;
import com.id.project.test_manulife.mapper.ClientDukcapilResponseMapper;
import com.id.project.test_manulife.repository.ClientDukcapilRepository;
import com.id.project.test_manulife.util.LoggingUtil;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientDukcapilService {

    private final ClientDukcapilRepository clientDukcapilRepository;
    private final ClientDukcapilResponseMapper clientDukcapilResponseMapper;

    private final static ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public BaseResponseDto<List<ClientDukcapilResponseDto>> getGroupedClientDukcapilByPolicyNumber(Long policyNumber) throws JsonProcessingException {
        DateTime start = DateTime.now();
        LoggingUtil.logRequest("GET_CLIENT_DUKCAPIL", "policyNumber: " + policyNumber);

        List<ClientDukcapilRaw> rawList = clientDukcapilRepository.executeGetClientDukcapilDetails(policyNumber);

        if (rawList.isEmpty()) {
            LoggingUtil.logResponse("GET_CLIENT_DUKCAPIL", "Data not found for policyNumber: " + policyNumber, start);
            return BaseResponseDto.notFound("Data policy number " + policyNumber + " tidak ditemukan.");
        }

        List<ClientDukcapilResponseDto> groupedResponses = rawList.stream()
                .collect(Collectors.groupingBy(ClientDukcapilRaw::getClientNumber))
                .values().stream()
                .map(clientRows -> {
                    ClientDukcapilRaw sample = clientRows.get(0);

                    List<String> linkTypes = clientRows.stream()
                            .map(ClientDukcapilRaw::getLinkType)
                            .distinct()
                            .toList();

                    return clientDukcapilResponseMapper.toResponse(sample, linkTypes);
                })
                .toList();

        LoggingUtil.logResponse("GET_CLIENT_DUKCAPIL", objectMapper.writeValueAsString(groupedResponses), start);
        return BaseResponseDto.success(groupedResponses, "Success fetching client Dukcapil details.");
    }
}