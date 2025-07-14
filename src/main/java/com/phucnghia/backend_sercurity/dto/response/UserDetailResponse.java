package com.phucnghia.backend_sercurity.dto.response;

import com.phucnghia.backend_sercurity.util.Gender;
import com.phucnghia.backend_sercurity.util.UserStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UserDetailResponse implements Serializable{
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date dateOfBirth;
    private Gender gender;
    private String username;
    private String type;
    private UserStatus status;
}
