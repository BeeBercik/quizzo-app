package com.quizzo.repository;

import com.quizzo.dto.AdminUserResponse;
import com.quizzo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByLogin(String login);

    Boolean existsByLogin(String login);

    Boolean existsByEmail(String email);

    @Query("""
            SELECT new com.quizzo.dto.AdminUserResponse(
                u.id,
                u.login,
                u.email,
                u.role,
                u.active,
                u.createTime,
                SIZE(u.attempts),
                SIZE(u.createdQuizzes)
            )
            FROM User u
            ORDER BY u.createTime DESC
            """)
    List<AdminUserResponse> findAllForAdminSortedByCreateTime();
}