package br.com.brito.springboot.mapper;

import br.com.brito.springboot.dto.UserDTO;
import br.com.brito.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toModel(UserDTO userDTO);

    UserDTO toDTO(User user);
}
