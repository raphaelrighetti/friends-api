package io.github.raphaelrighetti.friendsapi.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserAuthenticationDTO(
        @NotBlank @Email
        String email,
        @NotBlank
        String password
) {
}
