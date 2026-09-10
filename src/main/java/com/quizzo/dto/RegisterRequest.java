package com.quizzo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank
        @Size(min = QuizData.MIN_LOGIN_LENGTH,
                max = QuizData.MAX_LOGIN_LENGTH)
        String login,

        @NotBlank
        @Size(max = QuizData.MAX_EMAIL_LENGTH)
        @Email
        String email,

        @NotBlank
        @Size(min = QuizData.MIN_PASSWORD_LENGTH,
                max = QuizData.MAX_PASSWORD_LENGTH)
        String password) {
}