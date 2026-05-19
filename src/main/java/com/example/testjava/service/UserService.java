package com.example.testjava.service;

import com.example.testjava.dto.PageResponse;
import com.example.testjava.dto.user.UserCreateRequest;
import com.example.testjava.dto.user.UserResponse;
import com.example.testjava.dto.user.UserUpdateRequest;

public interface UserService {

    UserResponse createUser(UserCreateRequest request);

    UserResponse getUser(Long id);

    PageResponse<UserResponse> listUsers(int page, int size);

    UserResponse updateUser(Long id, UserUpdateRequest request);

    void deleteUser(Long id);
}
