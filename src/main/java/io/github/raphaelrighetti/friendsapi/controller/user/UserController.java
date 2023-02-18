package io.github.raphaelrighetti.friendsapi.controller.user;

import io.github.raphaelrighetti.friendsapi.dto.user.UserDetailingDTO;
import io.github.raphaelrighetti.friendsapi.dto.user.UserSignUpDTO;
import io.github.raphaelrighetti.friendsapi.service.user.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailingDTO> detail(@PathVariable Long id) {
        UserDetailingDTO dto = service.detail(id);

        return ResponseEntity.ok(dto);
    }
}
