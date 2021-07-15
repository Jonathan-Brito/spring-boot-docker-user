package br.com.brito.springboot.controller;


import br.com.brito.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
public class UserController implements UserControllerDocs {


    protected UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



}
