package com.Application2.in.BO;

import com.Application2.in.DTO.UserDTO;

public interface UserBO 
{
	public String getUserByIdWithRestTemplate(Long id);
	 public void addUserToFirstApplication(UserDTO userDTO);

}
