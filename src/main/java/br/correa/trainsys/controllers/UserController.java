package br.correa.trainsys.controllers;

import br.correa.trainsys.entities.User;
import br.correa.trainsys.services.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
