package com.phucnghia.backend_sercurity.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class TokenResponse implements Serializable {
    private String accessToken;
    private String refreshToken;
    private Long userId;

}
