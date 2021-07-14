package br.com.brito.springboot.dto;

import br.com.brito.springboot.entity.User;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Data
public class UserDTO {

    private Long id;

    private String name;

    private String email;

    private LocalDate birthData;

    public static UserDTO create(User user){
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(user, UserDTO.class);
    }
}
