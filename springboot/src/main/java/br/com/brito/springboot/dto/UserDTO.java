package br.com.brito.springboot.dto;

import br.com.brito.springboot.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthData;

    public static UserDTO create(User user){
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(user, UserDTO.class);
    }
}
