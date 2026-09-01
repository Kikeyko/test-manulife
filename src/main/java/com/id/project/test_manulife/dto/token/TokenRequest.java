package com.id.project.test_manulife.dto.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {

    @NotBlank(message = "client_id is required")
    @JsonProperty("client_id")
    private String clientId;

    @NotBlank(message = "client_secret is required")
    @JsonProperty("client_secret")
    private String clientSecret;

    @NotBlank(message = "grant_type is required")
    @JsonProperty("grant_type")
    private String grantType;
}