package br.com.brito.springboot.service;


import br.com.brito.springboot.mapper.UserMapper;
import br.com.brito.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    private final static UserMapper userMapper = UserMapper.INSTANCE;


    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
