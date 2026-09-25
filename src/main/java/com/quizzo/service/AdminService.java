package com.quizzo.service;

import com.quizzo.dto.AdminQuizResponse;
import com.quizzo.dto.AdminUserResponse;
import com.quizzo.exception.IncorrectUserDataException;
import com.quizzo.exception.QuizNotFoundException;
import com.quizzo.exception.UserNotFoundException;
import com.quizzo.model.Quiz;
import com.quizzo.model.Role;
import com.quizzo.model.User;
import com.quizzo.repository.QuizRepository;
import com.quizzo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final QuizRepository quizRepository;

    @Transactional(readOnly = true)
    public List<AdminUserResponse> getUsers() {
        return userRepository.findAll().stream()
                .sorted(Comparator.comparing(User::getId))
                .map(this::mapUserToAdminResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<AdminQuizResponse> getQuizzes() {
        return quizRepository.findAllQuizzesForAdminSortedByCreateTime();
    }

    @AdminAudit(action = "CHANGE USER ROLE")
    public void updateUserRole(Integer userId, Integer currentUserId, String roleValue) {
        if (userId.equals(currentUserId))
            throw new IncorrectUserDataException("You cannot change your own role");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Role role;
        try {
            role = Role.valueOf(roleValue.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IncorrectUserDataException("Incorrect role");
        }

        user.setRole(role);
        userRepository.save(user);
    }

    @AdminAudit(action = "DEACTIVATE USER")
    public void deactivateUser(Integer userId, Integer currentUserId) {
        setUserActive(userId, currentUserId, false);
    }

    @AdminAudit(action = "ACTIVATE USER")
    public void activateUser(Integer userId, Integer currentUserId) {
        setUserActive(userId, currentUserId, true);
    }

    private void setUserActive(Integer userId, Integer currentUserId, boolean active) {
        if (userId.equals(currentUserId))
            throw new IncorrectUserDataException("You cannot change your own account status");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setActive(active);
        userRepository.save(user);
    }

    @AdminAudit(action = "DEACTIVATE QUIZ")
    public void deactivateQuiz(String code) {
        setQuizActive(code, false);
    }

    @AdminAudit(action = "ACTIVATE QUIZ")
    public void activateQuiz(String code) {
        setQuizActive(code, true);
    }

    private void setQuizActive(String code, boolean active) {
        Quiz quiz = quizRepository.findByCode(code.trim().toUpperCase())
                .orElseThrow(() -> new QuizNotFoundException("Quiz " + code.toUpperCase() + " not found"));

        quiz.setActive(active);
        quizRepository.save(quiz);
    }

    private AdminUserResponse mapUserToAdminResponse(User user) {
        return new AdminUserResponse(
                user.getId(),
                user.getLogin(),
                user.getEmail(),
                user.getRole() == null ? Role.USER.name() : user.getRole().name(),
                !Boolean.FALSE.equals(user.getActive()),
                user.getCreateTime(),
                user.getAttempts().size(),
                user.getCreatedQuizzes().size()
        );
    }
}
