package com.brito.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brito.user.entities.User;
import com.brito.user.repository.UserRepository;

@Service
public class UserService {

		
	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Long id) throws Exception {
		Optional<User> optional = userRepository.findById(id);
		return optional.orElseThrow(() -> new Exception("Não existe usuário com id: " + id));
	}
	
	public User findByEmail(String email) throws Exception {
		Optional<User> optional = userRepository.findByEmail(email);
		return optional.orElseThrow(() -> new Exception("Não existe email: " + email));
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	
	
	public void deleteById(Long id) {
    
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
            
        }
        
    }

	

}
