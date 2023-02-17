package io.github.raphaelrighetti.friendsapi.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserSignUpDTO(
        @NotBlank
        String name,
        @NotNull
        Integer age,
        @NotBlank @Email
        String email,
        @NotBlank
        String password
) {
}
