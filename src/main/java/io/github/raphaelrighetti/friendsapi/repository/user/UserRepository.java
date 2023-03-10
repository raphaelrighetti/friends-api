package io.github.raphaelrighetti.friendsapi.repository.user;

import io.github.raphaelrighetti.friendsapi.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    Page<User> findByActiveTrue(Pageable pageable);
}
