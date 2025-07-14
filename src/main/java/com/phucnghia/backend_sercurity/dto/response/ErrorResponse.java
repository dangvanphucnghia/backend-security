package com.phucnghia.backend_sercurity.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class ErrorResponse implements Serializable {
    private Date timestamp;
    private int status;
    private String path;
    private String error;
    private String message;
}
