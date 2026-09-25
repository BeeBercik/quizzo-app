package com.quizzo.repository;

import com.quizzo.dto.AdminQuizResponse;
import com.quizzo.model.Quiz;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    boolean existsByCode(String code);

    @EntityGraph(attributePaths = "questions")
    Optional<Quiz> findByCode(String code);

    @Query("""
    SELECT new com.quizzo.dto.AdminQuizResponse(q.id, q.title, q.code, COALESCE(o.login, "-"), q.active, q.multipleChoice, SIZE(q.questions), SIZE(q.userAttempts), q.createTime)
    FROM Quiz q
    LEFT JOIN q.owner o
    ORDER BY q.createTime DESC
""")
    List<AdminQuizResponse> findAllQuizzesForAdminSortedByCreateTime();

}