package com.brito.user.dto;

import java.time.LocalDate;

import com.sun.istack.NotNull;

public class UserDTO {
	
	@NotNull
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String email;
	
	@NotNull
	private LocalDate birthDate;

}
