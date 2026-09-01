package com.id.project.test_manulife.model.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponseDto<T> {

    private String responseCode;
    private String responseMessage;
    private T data;

    public static <T> BaseResponseDto<T> success(T data, String message) {
        return BaseResponseDto.<T>builder()
                .responseCode("200")
                .responseMessage(message)
                .data(data)
                .build();
    }

    public static <T> BaseResponseDto<T> notFound(String message) {
        return BaseResponseDto.<T>builder()
                .responseCode("404")
                .responseMessage(message)
                .data(null)
                .build();
    }
}