package com.quizzo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record SubmittedAnswerRequest(
        @NotNull
        @PositiveOrZero
        Integer questionId,

        @NotEmpty
        List<@NotNull Integer> selectedAnswerIds) {
}