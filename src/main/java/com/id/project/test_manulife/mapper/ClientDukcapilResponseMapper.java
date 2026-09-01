package com.id.project.test_manulife.mapper;

import com.id.project.test_manulife.model.raw.dukcapil.ClientDukcapilRaw;
import com.id.project.test_manulife.model.dto.dukcapil.ClientDukcapilResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientDukcapilResponseMapper {

    public ClientDukcapilResponseDto toResponse(ClientDukcapilRaw raw, List<String> linkTypes) {
        return ClientDukcapilResponseDto.builder()
                .policyNumber(raw.getPolicyNumber())
                .linkTypes(linkTypes)
                .clientNumber(raw.getClientNumber())
                .clientName(raw.getClientName())
                .birthDate(raw.getBirthDate())
                .identityNumber(raw.getIdentityNumber())
                .genderCode(raw.getGenderCode())
                .identityNumberResult(raw.getIdentityNumberResult())
                .clientNameResult(raw.getClientNameResult())
                .birthDateResult(raw.getBirthDateResult())
                .genderCodeResult(raw.getGenderCodeResult())
                .summaryResult(raw.getSummaryResult())
                .summaryCode(raw.getSummaryCode())
                .resultCode(raw.getResultCode())
                .build();
    }

}