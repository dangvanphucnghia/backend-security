package com.phucnghia.backend_sercurity.dto.request;

import com.phucnghia.backend_sercurity.util.Flatform;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.io.Serializable;
@Getter
public class SignInrequest implements Serializable {

    @NotBlank(message = "username must be not blank")
    private String username;

    @NotBlank(message = "password must be not blank")
    private String password;

    @NotNull(message = "platforn must be not null")
    private Flatform flatform;

    private String deviceToken;

    private String version;
}
