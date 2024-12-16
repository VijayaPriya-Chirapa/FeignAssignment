package com.Application2.in.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.Application2.in.DTO.UserDTO;

@FeignClient(name = "application1", url = "${application1.service.url}")
public interface UserFeignClient {

    @GetMapping("/api/users/id/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);

}
