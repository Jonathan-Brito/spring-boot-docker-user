package br.com.brito.springboot.controller;

import br.com.brito.springboot.dto.UserDTO;
import br.com.brito.springboot.entity.User;
import br.com.brito.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "v1/users")
public class UserController {

    @Autowired
    protected UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity insert(@RequestBody User user){

        UserDTO userDTO = userService.insert(user); // Usuario que foi inserido

        URI location = getUri(user.getId());
        return ResponseEntity.created(location).build();
    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService(id, userDTO);
    }

    private UserDTO userService(Long id, UserDTO userDTO) {
        return userDTO;
    }

}
