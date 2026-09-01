package com.id.project.test_manulife.model.dto.dukcapil;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDukcapilResponseDto {

    @JsonProperty("policy_number")
    private Long policyNumber;

    @JsonProperty("link_types")
    private List<String> linkTypes;

    @JsonProperty("client_number")
    private Long clientNumber;

    @JsonProperty("client_name")
    private String clientName;

    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @JsonProperty("identity_number")
    private String identityNumber;

    @JsonProperty("gender_code")
    private String genderCode;

    @JsonProperty("identity_number_result")
    private String identityNumberResult;

    @JsonProperty("client_name_result")
    private String clientNameResult;

    @JsonProperty("birth_date_result")
    private String birthDateResult;

    @JsonProperty("gender_code_result")
    private String genderCodeResult;

    @JsonProperty("summary_result")
    private String summaryResult;

    @JsonProperty("summary_code")
    private String summaryCode;

    @JsonProperty("result_code")
    private String resultCode;

}