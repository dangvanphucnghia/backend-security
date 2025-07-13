package com.phucnghia.backend_sercurity.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.phucnghia.backend_sercurity.dto.validator.EnumPattern;
import com.phucnghia.backend_sercurity.dto.validator.EnumValue;
import com.phucnghia.backend_sercurity.dto.validator.GenderSubset;
import com.phucnghia.backend_sercurity.dto.validator.PhoneNumber;
import com.phucnghia.backend_sercurity.util.Gender;
import com.phucnghia.backend_sercurity.util.UserStatus;
import com.phucnghia.backend_sercurity.util.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import static com.phucnghia.backend_sercurity.util.Gender.*;

@Getter
public class UserRequest implements Serializable {
    @NotBlank(message = "firstName must bee not blank")
    private String firstName;

    @NotNull(message = "lastName must be not null")
    private String lastName;

    @Email(message = "email invalid format")
    private String email;

    @PhoneNumber(message = "phone invalid format")
    private String phone;

    @NotNull(message = "dateOfBirth must be not null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyyy")
    private Date dateOfBirth;

    @GenderSubset(anyOf = {MALE,FEMALE,OTHER})
    private Gender gender;

    @NotNull(message = "username must be not null")
    private String username;

    @NotNull(message = "password must be not null")
    private String password;

    @NotNull(message = "type must be not null")
    @EnumValue(name = "type", enumClass = UserType.class)
    private String type;

    @EnumPattern(name = "status", regexp = "ACTIVE|INACTIVE|NONE")
    private UserStatus status;

    @NotEmpty(message = "addresses can not empty")
    private Set<AddressRequest> addresses;

    public UserRequest(String firstName, String lastName, String email, String phone){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}
