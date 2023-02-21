package io.github.raphaelrighetti.friendsapi.dto.user;

import io.github.raphaelrighetti.friendsapi.entity.user.User;

public record UserDetailingDTO(
        Long id,
        String name,
        Integer age,
        String email,
        Boolean active
) {

    public UserDetailingDTO(User user) {
        this(user.getId(), user.getName(), user.getAge(), user.getEmail(), user.getActive());
    }
}
