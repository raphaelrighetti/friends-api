package io.github.raphaelrighetti.friendsapi.controller.user;

import io.github.raphaelrighetti.friendsapi.dto.user.UserDetailingDTO;
import io.github.raphaelrighetti.friendsapi.dto.user.UserListingDTO;
import io.github.raphaelrighetti.friendsapi.dto.user.UserUpdateDTO;
import io.github.raphaelrighetti.friendsapi.service.user.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Page<UserListingDTO>> list(@PageableDefault(sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(service.list(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailingDTO> detail(@PathVariable Long id) {
        return ResponseEntity.ok(service.detail(id));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<UserDetailingDTO> update(@RequestBody @Valid UserUpdateDTO data) {
        return ResponseEntity.ok(service.update(data));
    }

    @PostMapping("/activate/{id}")
    @Transactional
    public ResponseEntity<UserDetailingDTO> activate(@PathVariable Long id) {
        return ResponseEntity.ok(service.activate(id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<UserDetailingDTO> inactivate(@PathVariable Long id) {
        return ResponseEntity.ok(service.inactivate(id));
    }
}
