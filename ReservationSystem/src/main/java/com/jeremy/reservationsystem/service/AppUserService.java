package com.jeremy.reservationsystem.service;

import com.jeremy.reservationsystem.entity.AppUser;
import com.jeremy.reservationsystem.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * @author jeremy on 2022/12/6
 */
@Service
public class AppUserService {

    private final AppUserRepo appUserRepo;

    @Autowired
    public AppUserService(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    public void registerUser(AppUser user) {
        appUserRepo.save(user);
    }

    @Transactional
    public void updateUser(Integer id,
                           String username, 
                           String lastName, 
                           String firstName, 
                           String email) {
        AppUser user = appUserRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "User with id " + id + " doesn't exist"
                ));
        if (username != null && username.length() > 0
                && !Objects.equals(user.getUsername(), username)) {
            user.setUsername(username);
        }

        if (lastName != null && lastName.length() > 0
                && !Objects.equals(user.getLastName(), lastName)) {
            user.setLastName(lastName);
        }

        if (firstName != null && firstName.length() > 0
                && !Objects.equals(user.getFirstName(), firstName)) {
            user.setFirstName(firstName);
        }

        if (email != null && email.length() > 0
                && !Objects.equals(user.getEmail(), email)) {
            user.setEmail(email);
        }
    }

    public Optional<AppUser> getUser(Integer id) {
        return appUserRepo.findById(id);
    }

    public void deleteUser(Integer id) {
        boolean exists = appUserRepo.existsById(id);
        if (!exists) {
            throw new IllegalStateException("User with id " + id + " doesn't exist");
        }
        appUserRepo.deleteById(id);
    }

    public Optional<AppUser> findUserByEmail(String email) {
        return appUserRepo.findAppUserByEmail(email);
    }
}
