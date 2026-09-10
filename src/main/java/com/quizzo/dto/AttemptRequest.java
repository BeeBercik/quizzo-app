package com.quizzo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record AttemptRequest(
        @NotNull
        @PositiveOrZero
        Integer quizId,

        @NotNull
        List<@Valid @NotNull SubmittedAnswerRequest> answers) {
}