package com.quizzo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public record CreatedQuizRequest(
        @NotBlank(message = "Quiz title cannot be empty")
        @Size(min = QuizData.MIN_TITLE_LENGTH,
             max = QuizData.MAX_TITLE_LENGTH,
             message = "Quiz title must have between {min} and {max} characters")
        String title,


        @NotNull(message = "Quiz time cannot be empty")
        @Min(value = 1, message = "Quiz time must be at least {value} minute")
        @Max(value = QuizData.MAX_QUIZ_TIME, message = "Quiz time cannot be more than {value} minutes")
        Integer time,

        @NotNull(message = "Elimination count cannot be empty")
        @PositiveOrZero(message = "Elimination count cannot be negative")
        @Max(value = QuizData.MAX_ELIMINATIONS_COUNT,
                message = "Elimination count cannot be more than {value}")
        Integer eliminations,

        @NotNull(message = "Multiple choice value cannot be empty")
        Boolean multipleChoice,

        @NotEmpty(message = "Question data cannot be empty")
        @Size(max = QuizData.MAX_QUESTIONS_COUNT,
                message = "Max question count is {max}")
        List<@NotNull(message = "Question data cannot be empty") @Valid QuestionRequest> questionsData) {
}
