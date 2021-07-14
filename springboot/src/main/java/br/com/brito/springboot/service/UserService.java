package br.com.brito.springboot.service;

import br.com.brito.springboot.entity.User;
import br.com.brito.springboot.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected EntityManager entityManager;

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

    public User save(User user) {
        Optional<User> optional = userRepository.findById (user.getId());

        optional.ifPresent(value -> {
            entityManager.detach(value);
            user.setId(value.getId());
        });
        if (!optional.isPresent()) {
            user.setId(null);
        }
        userRepository.save(user);
        if (user.getId() == null) {

            user.setId(user.getId());
        }
        userRepository.save(user);

        return user;
    }


}
