package com.example.testjava.service;

import com.example.testjava.dto.user.UserCreateRequest;
import com.example.testjava.dto.user.UserResponse;
import com.example.testjava.dto.user.UserUpdateRequest;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserCreateRequest request);

    UserResponse getUser(Long id);

    List<UserResponse> listUsers();

    UserResponse updateUser(Long id, UserUpdateRequest request);

    void deleteUser(Long id);
}
