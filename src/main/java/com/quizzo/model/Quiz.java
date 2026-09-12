package com.quizzo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quizzo.dto.QuizData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = QuizData.MAX_TITLE_LENGTH, nullable = false)
    private String title;

    @Column(length = QuizData.CODE_LENGTH, nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private LocalDateTime createTime;

    @Column(nullable = false)
    private Integer durationTime;

    @Column(nullable = false)
    private Integer eliminationsCount;

    @Column(nullable = false)
    private Boolean multipleChoice;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User owner;

    @OneToMany(mappedBy = "quiz")
    private List<Attempt> userAttempts = new ArrayList<>();

    @OneToMany(mappedBy = "quiz",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", createTime=" + createTime +
                ", durationTime=" + durationTime +
                ", eliminationsCount=" + eliminationsCount +
                ", multipleChoice=" + multipleChoice +
                ", active=" + active +
                ", owner=" + owner.getId()   +
                ", userAttempts=" + userAttempts.size() +
                ", questions=" + questions +
                '}';
    }
}
