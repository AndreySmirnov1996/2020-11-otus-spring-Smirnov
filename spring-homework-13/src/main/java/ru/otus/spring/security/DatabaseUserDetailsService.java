package ru.otus.spring.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.AppUser;
import ru.otus.spring.repositories.UserRepository;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DatabaseUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<AppUser> user = userRepository.findByLogin(username);
        if (user.isEmpty()) {
            log.error("User, with login - '{}' not found", username);
            throw new UsernameNotFoundException(username);
        }
        log.info("User, with login - '{}' founded", username);
        return new UserDetailsImpl(user.get());
    }
}
