package com.Application2.in.Service;

import org.springframework.http.ResponseEntity;

import com.Application2.in.DTO.UserDTO;

public interface UserService 
{
	 public UserDTO getUserByIdWithRestTemplate(Long id);
	 public UserDTO getUserByIdWithFeignClient(Long id);
}
