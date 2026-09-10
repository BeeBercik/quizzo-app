package com.quizzo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AnswerRequest(
        @NotBlank(message = "Answer text cannot be empty")
        @Size(min = QuizData.MIN_ANSWER_LENGTH,
                max = QuizData.MAX_ANSWER_LENGTH,
                message = "Answer text must have between {min} and {max} characters")
        String value,

        @NotNull(message = "Answer correctness value cannot be empty")
        Boolean correct
) {}
