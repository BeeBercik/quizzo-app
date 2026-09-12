package com.quizzo.model;

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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = QuizData.MAX_LOGIN_LENGTH, nullable = false, unique = true)
    private String login;

    @Column(length = QuizData.PASSWORD_HASH_LENGTH, nullable = false)
    private String password;

    @Column(length = QuizData.MAX_EMAIL_LENGTH, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDateTime createTime;

    @Column(nullable = false)
    private Boolean active;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;

    @OneToMany(mappedBy = "owner",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Quiz> createdQuizzes = new ArrayList<>();

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Attempt> attempts = new ArrayList<>();

    public User(String login, String password, String email, LocalDateTime createTime, Boolean active, Role role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.createTime = createTime;
        this.active = active;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", active=" + active +
                ", role=" + role +
                '}';
    }
}
