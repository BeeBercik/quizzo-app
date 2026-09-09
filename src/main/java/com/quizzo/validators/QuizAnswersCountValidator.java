package com.quizzo.validators;

import com.quizzo.dto.AnswerRequest;
import com.quizzo.dto.CreatedQuizRequest;
import com.quizzo.exception.IncorrectQuizDataException;

public class QuizAnswersCountValidator {

    private static final Integer MAX_BAD_OPTIONS_COUNT = 8;
    private static final Integer MAX_CORRECT_OPTIONS_COUNT = 8;

    public static void validateQuizAnswersCount(CreatedQuizRequest updatedQuiz) {
        updatedQuiz.questionsData().forEach(questionData -> {
            int badOptions = 0;
            int correctOptions = 0;
            for (AnswerRequest a : questionData.answers()) {
                if (!a.correct()) badOptions++;
                else correctOptions++;
            }
            if (badOptions > MAX_BAD_OPTIONS_COUNT)
                throw new IncorrectQuizDataException("Max bad options count per question is " + MAX_BAD_OPTIONS_COUNT);
            if (badOptions == 0)
                throw new IncorrectQuizDataException("Question must have at least 1 bad option");
            if (correctOptions == 0)
                throw new IncorrectQuizDataException("Question must have at least 1 correct option");
            if (!updatedQuiz.multipleChoice() && correctOptions > 1)
                throw new IncorrectQuizDataException("Question cannot have more than 1 correct option in single-choice quiz");
            if (correctOptions > MAX_CORRECT_OPTIONS_COUNT)
                throw new IncorrectQuizDataException("Max correct options count per question is " + MAX_CORRECT_OPTIONS_COUNT);
        });
    }
}
