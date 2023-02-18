package io.github.raphaelrighetti.friendsapi.controller.user;

import io.github.raphaelrighetti.friendsapi.dto.user.UserDetailingDTO;
import io.github.raphaelrighetti.friendsapi.dto.user.UserSignUpDTO;
import io.github.raphaelrighetti.friendsapi.service.user.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    UserService service;

    @PostMapping
    @Transactional
    public ResponseEntity<UserDetailingDTO> signUp(@RequestBody @Valid UserSignUpDTO data, UriComponentsBuilder uriBuilder) {
        UserDetailingDTO dto = service.signUp(data);
        URI uri = uriBuilder.path("users/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }
}
