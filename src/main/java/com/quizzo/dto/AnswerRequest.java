package com.quizzo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AnswerRequest(
        @NotBlank
        @Size(min = QuizData.MIN_ANSWER_LENGTH,
                max = QuizData.MAX_ANSWER_LENGTH)
        String value,

        @NotNull
        Boolean correct
) {}