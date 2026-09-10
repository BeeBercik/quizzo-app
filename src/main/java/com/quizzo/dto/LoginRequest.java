package com.quizzo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
                @NotBlank(message = "Login cannot be empty")
                @Size(min = QuizData.MIN_LOGIN_LENGTH,
                        max = QuizData.MAX_LOGIN_LENGTH,
                        message = "Login must have between {min} and {max} characters")
                String login,

                @NotBlank(message = "Password cannot be empty")
                @Size(min = QuizData.MIN_PASSWORD_LENGTH,
                        max = QuizData.MAX_PASSWORD_LENGTH,
                        message = "Password must have between {min} and {max} characters")
                String password) {
}
