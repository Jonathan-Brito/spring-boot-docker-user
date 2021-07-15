package br.com.brito.springboot.service;

import br.com.brito.springboot.dto.UserDTO;
import br.com.brito.springboot.entity.User;
import br.com.brito.springboot.repository.UserRepository;
import javassist.compiler.ast.Expr;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {

    @Autowired
    protected UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User not found with id:" + id));
    }

    public UserDTO insert(User user) {
        Assert.isNull(user.getId(), "Não foi possível inserir o registro");

        return UserDTO.create(userRepository.save(user));
    }

    public UserDTO update(Long id, UserDTO userDTO) {
        userRepository.findById(id);

        return userDTO;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
