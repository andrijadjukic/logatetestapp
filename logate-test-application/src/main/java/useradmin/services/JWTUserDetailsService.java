package useradmin.services;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import useradmin.repositories.JWTUserDetailsRepository;

@Service
public class JWTUserDetailsService implements UserDetailsService {
    private final JWTUserDetailsRepository jwtUserDetailsRepository;

    public JWTUserDetailsService(JWTUserDetailsRepository jwtUserDetailsRepository) {
        this.jwtUserDetailsRepository = jwtUserDetailsRepository;
    }

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = getUserPasswordByUsername(username);
        if (!(password.equals(""))) {
            return new User(username, password,
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public String getUserPasswordByUsername(String username) {
        useradmin.domain.User user = jwtUserDetailsRepository.findByUsername(username)
                .orElseThrow(ResourceNotFoundException::new);
        return user.getPassword();
    }
}