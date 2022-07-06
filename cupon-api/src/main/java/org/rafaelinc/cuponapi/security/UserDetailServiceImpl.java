package org.rafaelinc.cuponapi.security;

import lombok.extern.slf4j.Slf4j;
import org.rafaelinc.cuponapi.model.User;
import org.rafaelinc.cuponapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("comming email: {}", username);
        User user = userRepository.findByEmail(username);

        if (isNull(user)){
            throw new UsernameNotFoundException(String.format("User not found for email %s", username));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRoles());
    }
}
