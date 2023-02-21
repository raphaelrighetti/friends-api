package io.github.raphaelrighetti.friendsapi.service.user;

import io.github.raphaelrighetti.friendsapi.dto.user.UserDetailingDTO;
import io.github.raphaelrighetti.friendsapi.dto.user.UserListingDTO;
import io.github.raphaelrighetti.friendsapi.dto.user.UserSignUpDTO;
import io.github.raphaelrighetti.friendsapi.dto.user.UserUpdateDTO;
import io.github.raphaelrighetti.friendsapi.entity.user.User;
import io.github.raphaelrighetti.friendsapi.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public UserDetailingDTO signUp(UserSignUpDTO data) {
        User user = new User(data);

        repository.save(user);

        return new UserDetailingDTO(user);
    }

    public UserDetailingDTO detail(Long id) {
        User user = repository.getReferenceById(id);

        return new UserDetailingDTO(user);
    }

    public Page<UserListingDTO> list(Pageable pageable) {
        return repository.findByActiveTrue(pageable).map(UserListingDTO::new);
    }

    public UserDetailingDTO update(UserUpdateDTO data) {
        User user = repository.getReferenceById(data.id());

        user.updateFields(data);

        return new UserDetailingDTO(user);
    }

    public UserDetailingDTO activate(Long id) {
        User user = repository.getReferenceById(id);

        user.activate();

        return new UserDetailingDTO(user);
    }

    public UserDetailingDTO inactivate(Long id) {
        User user = repository.getReferenceById(id);

        user.inactivate();

        return new UserDetailingDTO(user);
    }
}
