package com.quizzo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quizzo.dto.QuizData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "answers")
@Getter
@Setter
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = QuizData.MAX_ANSWER_LENGTH, nullable = false)
    private String value;

    @Column(nullable = false)
    private Boolean correct;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", correct=" + correct +
                ", question=" + question.getId() +
                '}';
    }
}