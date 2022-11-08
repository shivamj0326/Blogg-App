package com.harith.blog.payload;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min = 4, message ="Username must be minimum of 4 characters")
	private String name;
	
	@Email(message = "Email Address is not valid")
	private String email;
	
	@NotEmpty
	@Size(min = 6, max = 12, message = "Password must be minimum of 6 characters and maximum of 6 characters")
	private String password;
	
	@NotEmpty
	private String about;
}
