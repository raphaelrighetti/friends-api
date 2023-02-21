package io.github.raphaelrighetti.friendsapi.dto.user;

import io.github.raphaelrighetti.friendsapi.entity.user.User;

public record UserListingDTO(String name, Integer age) {
    public UserListingDTO(User user) {
        this(user.getName(), user.getAge());
    }
}
