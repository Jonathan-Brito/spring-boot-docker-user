package com.brito.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brito.user.entities.User;
import com.brito.user.request.UserRequest;
import com.brito.user.response.UserResponse;
import com.brito.user.response.UserResponse.UserResponseBuilder;
import com.brito.user.service.UserService;

@RestController
@RequestMapping(path = "/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest userRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        userService.save(user);

        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);

    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<User> listaUser = userService.findAll();

        List<UserResponse> listaUserResponse = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        
		listaUser.forEach(user -> listaUserResponse.add(mapper.map(user, UserResponse.class)));

        return ResponseEntity.ok(listaUserResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable(name = "id") Long id) {
        User user = null;
        try {
            user = userService.findById(id);

            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(user, userResponse);

            return ResponseEntity.ok().body(userResponse);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(UserResponse.builder().error(e.getMessage()).build());
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> findByEmail(@PathVariable(name = "email") String email) {
        try {
            User user = userService.findByEmail(email);

            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(user, userResponse);

            return ResponseEntity.ok().body(userResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(UserResponse.builder().error(e.getMessage()).build());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable(name = "id") Long id, @RequestBody UserRequest userRequest) {
        try {
            User user = userService.findById(id);
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setSkipNullEnabled(true);
            modelMapper.map(userRequest, user);
            user.setId(id);
            userService.save(user);

            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(user, userResponse);

            return ResponseEntity.status(HttpStatus.OK).body(userResponse);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(UserResponse.builder().error(e.getMessage()).build());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            UserResponseBuilder log = null;
			log.error(e.getMessage());
            return ResponseEntity.badRequest().body(UserResponse.builder().error("Usuário não existe ou invalido: " + id).build());
        }
    }

}
