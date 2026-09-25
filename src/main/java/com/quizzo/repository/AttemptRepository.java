package com.quizzo.repository;

import com.quizzo.model.Attempt;
import com.quizzo.model.Quiz;
import com.quizzo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt, Integer> {

    List<Attempt> findAllByUserOrderByAttemptTimeDesc(User user);

    @Query("""
         SELECT a FROM Attempt a
         JOIN FETCH a.user
         WHERE a.quiz.id = :quizId
         ORDER BY a.user.id, a.attemptTime DESC
""")
    List<Attempt> findAllWithUserByQuizId(Integer quizId);
}