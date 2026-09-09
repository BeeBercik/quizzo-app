package com.quizzo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public record CreatedQuizRequest(
        @NotBlank
        @Size(min = QuizData.MIN_TITLE_LENGTH,
             max = QuizData.MAX_TITLE_LENGTH)
        String title,


        @NotNull
        @Min(1)
        @Max(QuizData.MAX_QUIZ_TIME)
        Integer time,

        @NotNull
        @PositiveOrZero
        @Max(QuizData.MAX_ELIMINATIONS_COUNT)
        Integer eliminations,

        @NotNull
        Boolean multipleChoice,

        @NotEmpty
        @Size(max = QuizData.MAX_QUESTIONS_COUNT)
        List<@NotNull @Valid QuestionRequest> questionsData) {
}
