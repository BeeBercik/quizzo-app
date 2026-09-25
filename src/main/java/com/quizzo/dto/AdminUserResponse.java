package com.quizzo.dto;

import com.quizzo.model.Role;

import java.time.LocalDateTime;

public record AdminUserResponse(Integer id,
                                String login,
                                String email,
                                String role,
                                Boolean active,
                                LocalDateTime createTime,
                                Integer attemptsCount,
                                Integer createdQuizzesCount) {

    public AdminUserResponse(Integer id,
                             String login,
                             String email,
                             Role role,
                             Boolean active,
                             LocalDateTime createTime,
                             Integer attemptsCount,
                             Integer createdQuizzesCount) {
        this(
                id,
                login,
                email,
                role == null ? Role.USER.name() : role.name(),
                active,
                createTime,
                attemptsCount,
                createdQuizzesCount
        );
    }
}
