package com.quizzo.dto;

public record QuizAttemptDetailsResponse(String name,
                                         Integer durationTime,
                                         Integer questions,
                                         Integer eliminationsCount,
                                         Boolean multipleChoice) {
}
