package com.quizzo.repository;

import com.quizzo.model.Question;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @EntityGraph(attributePaths = "answers")
    List<Question> findAllByQuizId(Integer quizId);
}