package com.example.testjava.service.impl;

import com.example.testjava.common.ApiCode;
import com.example.testjava.dto.user.UserCreateRequest;
import com.example.testjava.dto.user.UserResponse;
import com.example.testjava.dto.user.UserUpdateRequest;
import com.example.testjava.entity.UserEntity;
import com.example.testjava.exception.BusinessException;
import com.example.testjava.repository.UserRepository;
import com.example.testjava.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserResponse createUser(UserCreateRequest request) {
        String username = normalizeUsername(request.getUsername());
        if (userRepository.existsByUsername(username)) {
            throw new BusinessException(ApiCode.CONFLICT, "Username already exists: " + username);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setEmail(trimToNull(request.getEmail()));
        userEntity.setAge(request.getAge());

        return toResponse(userRepository.save(userEntity));
    }

    @Override
    public UserResponse getUser(Long id) {
        return toResponse(findUser(id));
    }

    @Override
    public List<UserResponse> listUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        UserEntity userEntity = findUser(id);
        String username = normalizeUsername(request.getUsername());

        userRepository.findByUsername(username)
                .filter(existingUser -> !existingUser.getId().equals(id))
                .ifPresent(existingUser -> {
                    throw new BusinessException(ApiCode.CONFLICT, "Username already exists: " + username);
                });

        userEntity.setUsername(username);
        userEntity.setEmail(trimToNull(request.getEmail()));
        userEntity.setAge(request.getAge());

        return toResponse(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        UserEntity userEntity = findUser(id);
        userRepository.delete(userEntity);
    }

    private UserEntity findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ApiCode.NOT_FOUND, "User not found: " + id));
    }

    private String normalizeUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new BusinessException(ApiCode.BAD_REQUEST, "Username must not be blank");
        }
        return username.trim();
    }

    private String trimToNull(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return value.trim();
    }

    private UserResponse toResponse(UserEntity userEntity) {
        UserResponse response = new UserResponse();
        response.setId(userEntity.getId());
        response.setUsername(userEntity.getUsername());
        response.setEmail(userEntity.getEmail());
        response.setAge(userEntity.getAge());
        response.setCreatedAt(userEntity.getCreatedAt());
        response.setUpdatedAt(userEntity.getUpdatedAt());
        return response;
    }
}
