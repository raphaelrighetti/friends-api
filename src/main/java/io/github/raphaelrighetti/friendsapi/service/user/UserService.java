package io.github.raphaelrighetti.friendsapi.service.user;

import io.github.raphaelrighetti.friendsapi.dto.user.UserDetailingDTO;
import io.github.raphaelrighetti.friendsapi.dto.user.UserSignUpDTO;
import io.github.raphaelrighetti.friendsapi.entity.user.User;
import io.github.raphaelrighetti.friendsapi.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public UserDetailingDTO detail(Long id) {
        User user = repository.getReferenceById(id);

        return new UserDetailingDTO(user);
    }

    public UserDetailingDTO signUp(UserSignUpDTO data) {
        User user = new User(data);

        repository.save(user);

        return new UserDetailingDTO(user);
    }
}
