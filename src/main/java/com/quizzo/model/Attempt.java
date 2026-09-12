package com.quizzo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "attempts")
@Getter
@Setter
@NoArgsConstructor
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JsonIgnore
    private Quiz quiz;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private LocalDateTime attemptTime;

    public Attempt(User user, Quiz quiz, Integer score, LocalDateTime attemptTime) {
        this.user = user;
        this.quiz = quiz;
        this.score = score;
        this.attemptTime = attemptTime;
    }

    @Override
    public String toString() {
        return "Attempt{" +
                "id=" + id +
                ", user=" + user.getId() +
                ", quiz=" + quiz.getId() +
                ", score=" + score +
                ", attemptTime=" + attemptTime +
                '}';
    }
}