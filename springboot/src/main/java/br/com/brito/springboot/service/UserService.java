package br.com.brito.springboot.service;

import br.com.brito.springboot.dto.UserDTO;
import br.com.brito.springboot.entity.User;
import br.com.brito.springboot.repository.UserRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    @Autowired
    protected UserRepository userRepository;


    public List<UserDTO> getUsers(){
        List<UserDTO> list = userRepository.findAll().stream().map(UserDTO::create).collect(Collectors.toList());

        return list;

    }

    public Optional<UserDTO> getUserById(Long id){
        Optional<User> userDTO = userRepository.findById(id);
        return userDTO.map(UserDTO::create);

    }

    public UserDTO insert(User user){
        Assert.isNull(user.getId(), "Não foi possível inserir o registro");

        return UserDTO.create(userRepository.save(user));
    }

    public UserDTO update(User user, Long id){
        Assert.notNull(id, "Não foi possível atualizar o registro");

        //Busaca o usuario no banco de dados
        Optional<User> optional = userRepository.findById(id);

        if (optional.isPresent()){
            User db = optional.get();

            //Copia as propriedades
            db.setName(user.getName());
            db.setEmail(user.getEmail());
            db.setBirthData(user.getBirthData());
            System.out.println("User id " + db.getId());

            //Atualiza o usuario
            userRepository.save(db);

            return UserDTO.create(db);
        }
        else {
            throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id){

        userRepository.deleteById(id);

    }
}
