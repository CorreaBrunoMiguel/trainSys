package br.correa.trainsys.controllers;

import br.correa.trainsys.entities.User;
import br.correa.trainsys.services.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User post(
            @RequestBody User user
            ) throws BadRequestException {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAll(){
        return userService.userList();
    }

    @GetMapping("/{id}")
    public Optional<User> getById(@PathVariable Long id) throws BadRequestException {
        return userService.userById(id);
    }
}
