package io.github.raphaelrighetti.friendsapi.controller.authentication;

import io.github.raphaelrighetti.friendsapi.dto.user.UserAuthenticationDTO;
import io.github.raphaelrighetti.friendsapi.entity.user.User;
import io.github.raphaelrighetti.friendsapi.service.authentication.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticate(@RequestBody @Valid UserAuthenticationDTO data) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                data.email(),
                data.password()
        );
        Authentication authentication = manager.authenticate(authenticationToken);
        String jwtToken = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new JWTTokenDTO(jwtToken));
    }

    private record JWTTokenDTO(String token) {
    }
}
