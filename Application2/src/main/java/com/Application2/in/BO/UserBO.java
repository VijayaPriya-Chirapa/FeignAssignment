package com.Application2.in.BO;

import org.springframework.http.ResponseEntity;

import com.Application2.in.DTO.UserDTO;

public interface UserBO 
{
	public UserDTO getUserByIdWithRestTemplate(Long id);
	

}
