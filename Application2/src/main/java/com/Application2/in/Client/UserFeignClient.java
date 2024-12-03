package com.Application2.in.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.Application2.in.DTO.UserDTO;

@FeignClient(name = "userClient", url = "http://localhost:8080/api/users")
public interface UserFeignClient
{
	@GetMapping("/id/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);

    @PostMapping("/add")
    String addUser(@RequestBody UserDTO userDTO);
}

