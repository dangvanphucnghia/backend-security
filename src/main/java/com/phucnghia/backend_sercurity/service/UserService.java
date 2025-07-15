package com.phucnghia.backend_sercurity.service;

import com.phucnghia.backend_sercurity.dto.request.UserRequest;
import com.phucnghia.backend_sercurity.dto.response.PageResponse;
import com.phucnghia.backend_sercurity.dto.response.UserDetailResponse;
import com.phucnghia.backend_sercurity.model.User;
import com.phucnghia.backend_sercurity.util.UserStatus;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();

    User getByUsername(String userName);

    long saveUser(UserRequest request);

    void updateUser(long userId, UserRequest request);

    void changeStatus(long userId, UserStatus status);

    void deleteUser(long userId);

    UserDetailResponse getUser(long userId);

    PageResponse<UserDetailResponse> getAllUsers(String keyword, String sortField, String sortDir, int page, int size);

    List<String> findAllRolesByUserId(long userId);
}
