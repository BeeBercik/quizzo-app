package com.quizzo.validation;

import com.quizzo.dto.AnswerRequest;
import com.quizzo.dto.CreatedQuizRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidQuizAnswersValidator implements ConstraintValidator<ValidQuizAnswers, CreatedQuizRequest> {

    private static final int MAX_BAD_OPTIONS_COUNT = 8;
    private static final int MAX_CORRECT_OPTIONS_COUNT = 8;

    @Override
    public boolean isValid(CreatedQuizRequest quizRequest, ConstraintValidatorContext context) {
        if (quizRequest == null
                || quizRequest.questionsData() == null
                || quizRequest.multipleChoice() == null) {
            return true;
        }

        for (int i = 0; i < quizRequest.questionsData().size(); i++) {
            var questionData = quizRequest.questionsData().get(i);

            if (questionData == null || questionData.answers() == null) {
                continue;
            }

            int badOptions = 0;
            int correctOptions = 0;
            boolean hasIncompleteAnswer = false;
            for (AnswerRequest answer : questionData.answers()) {
                if (answer == null || answer.correct() == null) {
                    hasIncompleteAnswer = true;
                    break;
                }

                if (answer.correct()) {
                    correctOptions++;
                } else {
                    badOptions++;
                }
            }

            if (hasIncompleteAnswer) {
                continue;
            }

            if (badOptions > MAX_BAD_OPTIONS_COUNT) {
                return invalid(context, i, "Max bad options count per question is " + MAX_BAD_OPTIONS_COUNT);
            }
            if (badOptions == 0) {
                return invalid(context, i, "Question must have at least 1 bad option");
            }
            if (correctOptions == 0) {
                return invalid(context, i, "Question must have at least 1 correct option");
            }
            if (!quizRequest.multipleChoice() && correctOptions > 1) {
                return invalid(context, i, "Question cannot have more than 1 correct option in single-choice quiz");
            }
            if (correctOptions > MAX_CORRECT_OPTIONS_COUNT) {
                return invalid(context, i, "Max correct options count per question is " + MAX_CORRECT_OPTIONS_COUNT);
            }
        }

        return true;
    }

    private boolean invalid(ConstraintValidatorContext context, int questionIndex, String message) {
        context.disableDefaultConstraintViolation();

        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode("questionsData")
                .inIterable()
                .atIndex(questionIndex)
                .addPropertyNode("answers")
                .addConstraintViolation();

        return false;
    }
}
