package com.phucnghia.backend_sercurity.service.impl;

import com.phucnghia.backend_sercurity.configuration.Translator;
import com.phucnghia.backend_sercurity.dto.request.AddressRequest;
import com.phucnghia.backend_sercurity.dto.request.UserRequest;
import com.phucnghia.backend_sercurity.dto.response.PageResponse;
import com.phucnghia.backend_sercurity.dto.response.UserDetailResponse;
import com.phucnghia.backend_sercurity.exception.ResourceNotFoundException;
import com.phucnghia.backend_sercurity.model.Address;
import com.phucnghia.backend_sercurity.model.User;
import com.phucnghia.backend_sercurity.repository.UserRepository;
import com.phucnghia.backend_sercurity.service.UserService;
import com.phucnghia.backend_sercurity.util.UserStatus;
import com.phucnghia.backend_sercurity.util.UserType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "USER-SERVICE")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Translator translator;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User getByUsername(String userName) {
        return userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Save new user to DB
     *
     * @param request
     * @return userId
     */
    @Override
    public long saveUser(UserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        userRepository.save(user);

        log.info("User has added successfully, userId={}", user.getId());

        return user.getId();
    }

    /**
     * Update user by userId
     *
     * @param userId
     * @param request
     */
    @Override
    public void updateUser(long userId, UserRequest request) {
        User user = getUserById(userId);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setGender(request.getGender());
        user.setPhone(request.getPhone());
        if (!request.getEmail().equals(user.getEmail())) {
            // check email from database if not exist then allow update email otherwise throw exception
            user.setEmail(request.getEmail());
        }
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setStatus(request.getStatus());
        user.setType(UserType.valueOf(request.getType().toUpperCase()));
        user.setAddresses(convertToAddress(request.getAddresses()));
        userRepository.save(user);

        log.info("User has updated successfully, userId={}", userId);
    }

    /**
     * Change status of user by userId
     *
     * @param userId
     * @param status
     */
    @Override
    public void changeStatus(long userId, UserStatus status) {
        User user = getUserById(userId);
        user.setStatus(status);
        userRepository.save(user);

        log.info("User status has changed successfully, userId={}", userId);
    }

    /**
     * Delete user by userId
     *
     * @param userId
     */
    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
        log.info("User has deleted permanent successfully, userId={}", userId);
    }

    /**
     * Get user detail by userId
     *
     * @param userId
     * @return
     */
    @Override
    public UserDetailResponse getUser(long userId) {
        User user = getUserById(userId);
        return getUserDetailResponse(user);
    }

    @Override
    public PageResponse<UserDetailResponse> getAllUsers(String keyword, String sortField, String sortDir, int page, int size) {

        int pageNumber = (page > 0) ? page - 1 : 0;
        sortField = (sortField != null) ? sortField : "id";
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(direction, sortField));

        Page<User> entityPage;

        if (StringUtils.hasLength(keyword)) {
            entityPage = userRepository.searchByKeyword(keyword, pageable);
        } else {
            entityPage = userRepository.findAll(pageable);
        }

        List<UserDetailResponse> list = entityPage.stream().map(this::getUserDetailResponse).toList();

        return PageResponse.<UserDetailResponse>builder()
                .pageNumber(page)
                .pageSize(size)
                .totalElements(entityPage.getTotalElements())
                .totalPages(entityPage.getTotalPages())
                .data(list)
                .build();
    }

    @Override
    public List<String> findAllRolesByUserId(long userId) {
        return userRepository.findAllRolesByUserId(userId);
    }

    /**
     * Get user by userId
     *
     * @param userId
     * @return User
     */
    private User getUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(translator.toLocale("user.not.found")));
    }

    /**
     * Covert Set<AddressRequest> to Set<Address>
     *
     * @param addresses
     * @return Set<Address>
     */
    private Set<Address> convertToAddress(Set<AddressRequest> addresses) {
        Set<Address> result = new HashSet<>();
        for (AddressRequest addressRequest : addresses) {
            Address address = new Address();
            BeanUtils.copyProperties(addressRequest, address);
            result.add(address);
        }

        return result;
    }

    /**
     * Convert User to UserDetailResponse
     *
     * @param user
     * @return
     */
    private UserDetailResponse getUserDetailResponse(User user) {
        UserDetailResponse userDetailResponse = new UserDetailResponse();
        BeanUtils.copyProperties(user, userDetailResponse);
        return userDetailResponse;
    }
}