package com.jeremy.reservationsystem.repo;

import com.jeremy.reservationsystem.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author jeremy on 2022/12/6
 */
public interface AppUserRepo extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findAppUserByEmail(String email);
}
