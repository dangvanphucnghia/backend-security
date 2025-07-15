package com.phucnghia.backend_sercurity.controller;

import com.phucnghia.backend_sercurity.dto.request.UserRequest;
import com.phucnghia.backend_sercurity.dto.response.ApiResponse;
import com.phucnghia.backend_sercurity.service.UserService;
import com.phucnghia.backend_sercurity.util.UserStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User Controller")
@Slf4j(topic = "USER-CONTROLLER")
public class UserController {

    private final UserService userService;

    @Operation(method = "POST", summary = "Add new user", description = "Send a request via this API to create new user")
    @PostMapping(value = "/")
    public ApiResponse addUser(@Valid @RequestBody UserRequest request) {
        return ApiResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("User added successfully")
                .data(userService.saveUser(request))
                .build();
    }

    @Operation(summary = "Update user", description = "Send a request via this API to update user")
    @PutMapping("/{userId}")
    public ApiResponse updateUser(@PathVariable @Min(1) long userId, @Valid @RequestBody UserRequest request) {
        userService.updateUser(userId, request);
        return ApiResponse.builder()
                .status(HttpStatus.ACCEPTED.value())
                .message("User updated successfully")
                .build();
    }

    @Operation(summary = "Change status of user", description = "Send a request via this API to change status of user")
    @PatchMapping("/{userId}")
    public ApiResponse updateStatus(@Min(1) @PathVariable int userId, @RequestParam UserStatus status) {
        userService.changeStatus(userId, status);
        return ApiResponse.builder()
                .status(HttpStatus.ACCEPTED.value())
                .message("User status changed successfully")
                .build();
    }

    @Operation(summary = "Delete user permanently", description = "Send a request via this API to delete user permanently")
    @DeleteMapping("/{userId}")
    public ApiResponse deleteUser(@PathVariable @Min(value = 1, message = "userId must be greater than 0") long userId) {
        userService.deleteUser(userId);
        return ApiResponse.builder()
                .status(HttpStatus.NO_CONTENT.value())
                .message("User deleted successfully")
                .build();
    }

    @Operation(summary = "Get user detail", description = "Send a request via this API to get user information")
    @GetMapping("/{userId}")
    public ApiResponse getUser(@PathVariable @Min(1) long userId) {
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Get user detail successfully")
                .data(userService.getUser(userId))
                .build();
    }

    @Operation(summary = "Get list of users per pageNo", description = "Send a request via this API to get user list by pageNo and pageSize")
    @GetMapping("/list")
    public ApiResponse getAllUsers(@RequestParam(required = false) String keyword,
                                   @RequestParam(defaultValue = "id") String sortField,
                                   @RequestParam(defaultValue = "desc") String sortDir,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Get user list successfully")
                .data(userService.getAllUsers(keyword, sortField, sortDir, page, size))
                .build();
    }
}
