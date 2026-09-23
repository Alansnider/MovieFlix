package com.movieflix.mapper;

import com.movieflix.dto.request.RegisterUserRequest;
import com.movieflix.dto.response.UserResponse;
import com.movieflix.entity.User;

public final class UserMapper {

    private UserMapper() {
    }

    public static User toUser(RegisterUserRequest request) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}
