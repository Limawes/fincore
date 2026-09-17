package br.com.fincore.security.service.auth;

import br.com.fincore.security.repository.UserReposiory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    private final UserReposiory userReposiory;

    public AuthorizationService(UserReposiory userReposiory) {
        this.userReposiory = userReposiory;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userReposiory.findByEmail(email);
    }
}
