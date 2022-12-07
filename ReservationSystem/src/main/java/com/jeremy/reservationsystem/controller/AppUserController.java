package com.jeremy.reservationsystem.controller;

import com.jeremy.reservationsystem.entity.AppUser;
import com.jeremy.reservationsystem.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author jeremy on 2022/12/6
 */
@RestController
@RequestMapping(path = "/api/user")
public class AppUserController {
    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public Optional<AppUser> getUser(@RequestParam Integer id) {
        return appUserService.getUser(id);
    }

    @PostMapping
    public void registerUser(@RequestBody AppUser user) {
        appUserService.registerUser(user);
    }

    @PutMapping(path = "/{id}")
    public void updateUser(@PathVariable("id") Integer id,
                           @RequestParam(required = false) String username,
                           @RequestParam(required = false) String lastName,
                           @RequestParam(required = false) String firstName,
                           @RequestParam(required = false) String email) {
        appUserService.updateUser(id, username, lastName, firstName, email);
    }

    @DeleteMapping()
    public void deleteUser(@RequestParam Integer id) {
        appUserService.deleteUser(id);
    }
}
