package com.quizzo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record AttemptRequest(
        @NotNull(message = "Quiz id cannot be empty")
        @PositiveOrZero(message = "Quiz id cannot be negative")
        Integer quizId,

        @NotNull(message = "Attempt answers cannot be null")
        List<@Valid @NotNull(message = "Submitted answer data cannot be empty") SubmittedAnswerRequest> answers) {
}
