package com.Application2.in.RestTemplate;

import com.Application2.in.DTO.UserDTO;

public interface UserRestTemplate {
	UserDTO getUserByIdWithRestTemplate(Long id);
}
