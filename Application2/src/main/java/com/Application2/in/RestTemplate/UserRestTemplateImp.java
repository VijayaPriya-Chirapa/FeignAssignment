package com.Application2.in.RestTemplate;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.Application2.in.DTO.UserDTO;

@Component
public class UserRestTemplateImp implements UserRestTemplate {

	private final RestTemplate restTemplate=new RestTemplate();
	@Override
	public UserDTO getUserByIdWithRestTemplate(Long id) {
		 String url = "http://localhost:9090/api/users/id/"+id;
		 return restTemplate.getForObject(url, UserDTO.class);
	}

}
