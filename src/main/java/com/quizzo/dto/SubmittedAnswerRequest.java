package com.quizzo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record SubmittedAnswerRequest(
        @NotNull(message = "Question id cannot be empty")
        @PositiveOrZero(message = "Question id cannot be negative")
        Integer questionId,

        @NotEmpty(message = "At least one answer must be selected")
        List<@NotNull(message = "Selected answer id cannot be empty") Integer> selectedAnswerIds) {
}
