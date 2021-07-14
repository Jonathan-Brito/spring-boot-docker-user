package br.com.brito.springboot.controller;

import br.com.brito.springboot.dto.UserDTO;
import br.com.brito.springboot.entity.User;
import br.com.brito.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "v1/users")
public class UserController {

    @Autowired
    protected UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getUsers(){
        List<UserDTO> users = userService.getUsers();

        return ResponseEntity.ok(users);

    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable("id") Long id){
        Optional<UserDTO> userDTO = userService.getUserById(id);

        return ResponseEntity.ok(userDTO);
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
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody User user){

        user.setId(id);

        UserDTO userDTO = userService.insert(user);

        return user != null ?
                ResponseEntity.ok(userDTO) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        userService.delete(id);

        return ResponseEntity.ok().build();

    }
}
