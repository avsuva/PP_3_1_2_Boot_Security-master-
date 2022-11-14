package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;

@Component
public class baseUsers implements ApplicationRunner {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoderConfig pass;

    @Autowired
    public baseUsers(UserService userService, RoleService roleService, PasswordEncoderConfig pass) {
        this.userService = userService;
        this.roleService = roleService;
        this.pass = pass;
    }

    private void addRole(){
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleService.addRole(roleUser);
        roleService.addRole(roleAdmin);
    }
    private void addUser(){
        User user = new User("user","user", "user@yandex.ru",List.of(roleService.getByIdRoles(1)));
        User admin = new User("admin", "admin","admin@yandex.ru", List.of(roleService.getByIdRoles(2)));
        userService.add(user);
        userService.add(admin);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        addRole();
        addUser();
    }

}
