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

    int MIN_LOGIN_LENGTH = 8;
    int MAX_LOGIN_LENGTH = 20;
    int MIN_PASSWORD_LENGTH = 10;
    int MAX_PASSWORD_LENGTH = 40;
    int PASSWORD_HASH_LENGTH = 60;
    int MAX_EMAIL_LENGTH = 40;
}
