package io.github.raphaelrighetti.friendsapi.repository.user;

import io.github.raphaelrighetti.friendsapi.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);
}
