package com.quizzo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record QuestionRequest(
        @NotBlank(message = "Question text cannot be empty")
        @Size(min = QuizData.MIN_QUESTION_LENGTH,
                max = QuizData.MAX_QUESTION_LENGTH,
                message = "Question text must have between {min} and {max} characters")
        String question,

        @NotEmpty(message = "Question must have answers")
        @Size(max = QuizData.MAX_ANSWERS_COUNT,
                message = "Max answers count per question is {max}")
        List<@NotNull(message = "Answer data cannot be empty") @Valid AnswerRequest> answers) {
}
