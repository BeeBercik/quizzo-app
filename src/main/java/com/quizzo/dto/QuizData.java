package com.quizzo.dto;

public interface QuizData {
    int CODE_LENGTH = 5;
    int MAX_QUIZ_TIME = 300;
    int MAX_ELIMINATIONS_COUNT = 99;

    int MIN_QUESTION_LENGTH = 3;
    int MAX_QUESTION_LENGTH = 70;
    int MAX_QUESTIONS_COUNT = 100;


    int MIN_ANSWER_LENGTH = 3;
    int MAX_ANSWER_LENGTH = 70;
    int MAX_ANSWERS_COUNT = 100;

    int MIN_TITLE_LENGTH = 3;
    int MAX_TITLE_LENGTH = 40;
}
