package com.quizzo.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleUpdateRequest(
        @NotBlank(message = "Role cannot be empty")
        String role) {
}
