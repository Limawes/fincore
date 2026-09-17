package br.com.fincore.security.repository;

import br.com.fincore.security.domain.model.UserSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserReposiory extends JpaRepository<UserSite, Long> {
    UserDetails findByEmail(String email);

}
