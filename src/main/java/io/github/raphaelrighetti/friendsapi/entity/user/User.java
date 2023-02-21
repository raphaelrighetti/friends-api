package io.github.raphaelrighetti.friendsapi.entity.user;

import io.github.raphaelrighetti.friendsapi.dto.user.UserSignUpDTO;
import io.github.raphaelrighetti.friendsapi.dto.user.UserUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Table(name = "user")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"email"})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String password;
    private Boolean active = true;

    public User(UserSignUpDTO data) {
        name = data.name();
        age = data.age();
        email = data.email();
        password = encodePassword(data.password());
    }

    public void updateFields(UserUpdateDTO data) {
        if (data.name() != null) name = data.name();
        if (data.age() != null) age = data.age();
        if (data.email() != null) email = data.email();
        if (data.password() != null) password = encodePassword(data.password());
    }

    public void activate() {
        active = true;
    }

    public void inactivate() {
        active = false;
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder(10).encode(password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
