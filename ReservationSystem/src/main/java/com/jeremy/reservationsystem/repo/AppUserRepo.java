package com.jeremy.reservationsystem.repo;

import com.jeremy.reservationsystem.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jeremy on 2022/12/6
 */
public interface AppUserRepo extends JpaRepository<AppUser, Integer> {
}
