package com.id.project.test_manulife.model.raw.dukcapil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDukcapilRaw {
    private Long policyNumber;
    private String linkType;
    private Long clientNumber;
    private String clientName;
    private LocalDate birthDate;
    private String identityNumber;
    private String genderCode;
    private String identityNumberResult;
    private String clientNameResult;
    private String birthDateResult;
    private String genderCodeResult;
    private String summaryResult;
    private String summaryCode;
    private String resultCode;
}
