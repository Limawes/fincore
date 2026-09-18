package br.com.fincore.security.service;

import br.com.fincore.security.domain.model.UserSite;
import br.com.fincore.security.domain.request.UserDTO;
import br.com.fincore.security.repository.UserReposiory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {

    private final UserReposiory repository;

    public UserAuthService(UserReposiory repository) {
        this.repository = repository;
    }

    public Long registerUser(UserDTO userDTO){
        var user = repository.findByEmail(userDTO.email());
        if (user == null) {
            var newUser = this.repository.save(convertToEntity(userDTO));
            return newUser.getId();
        }
        return 0L;
    }

    private UserSite convertToEntity(UserDTO userDTO) {
        UserSite user = new UserSite();
        user.setEmail(userDTO.email());
//        user.setPassword(encryptPassword(userDTO.password()));
        var encryptPassword = new BCryptPasswordEncoder().encode(userDTO.password());
        user.setPassword(encryptPassword);
        user.setRole(userDTO.role());
        user.setActive(true);

        return user;
    }

    private String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
