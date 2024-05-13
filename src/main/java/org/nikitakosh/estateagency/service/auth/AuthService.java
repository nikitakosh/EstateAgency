package org.nikitakosh.estateagency.service.auth;

import lombok.extern.slf4j.Slf4j;
import org.nikitakosh.estateagency.controller.dto.SignInDto;
import org.nikitakosh.estateagency.controller.dto.SignUpDto;
import org.nikitakosh.estateagency.exceptions.InvalidJwtException;
import org.nikitakosh.estateagency.models.User;
import org.nikitakosh.estateagency.models.enums.UserRole;
import org.nikitakosh.estateagency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService implements UserDetailsService {


    private final UserRepository userRepository;
    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByLogin(username);
    }

    public void signUp(SignUpDto data) throws InvalidJwtException {
        if (userRepository.findByLogin(data.login()) != null) {
            throw new InvalidJwtException("Username already exists");
        }
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, UserRole.USER);
        userRepository.save(newUser);
    }

    public Boolean signIn(SignInDto data) {
        User user = (User) userRepository.findByLogin(data.login());
        if (user == null) {
            log.info("no such user");
            return false;
        }
        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return true;
    }
}