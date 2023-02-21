package io.github.raphaelrighetti.friendsapi.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserUpdateDTO(
        @NotNull
        Long id,
        String name,
        Integer age,
        @Email
        String email,
        String password
) {
}
