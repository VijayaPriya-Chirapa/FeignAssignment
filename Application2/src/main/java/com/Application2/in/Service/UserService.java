package com.Application2.in.Service;

import com.Application2.in.DTO.UserDTO;

public interface UserService 
{
	 public String getUserByIdWithRestTemplate(Long id);
	 public void addUserToFirstApplication(UserDTO userDTO);

}
