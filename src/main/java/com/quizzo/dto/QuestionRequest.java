package com.quizzo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record QuestionRequest(
        @NotBlank
        @Size(min = QuizData.MIN_QUESTION_LENGTH,
                max = QuizData.MAX_QUESTION_LENGTH)
        String question,

        @NotEmpty
        @Size(max = QuizData.MAX_ANSWERS_COUNT)
        List<@NotNull @Valid AnswerRequest> answers) {
}