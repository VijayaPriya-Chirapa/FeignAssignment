package com.Application2.in.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Application2.in.util.Constants;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import com.Application2.in.DTO.UserDTO;
import com.Application2.in.Service.UserService;


@RestController
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(Constants.LOGGER_NAME_CONTROLLER);

    @Autowired
    private UserService userService;
 
    @GetMapping("/getUserRestTemplate/{id}")
    @Async
    @CircuitBreaker(name = "userServiceCircuitBreaker", fallbackMethod = "fallbackGetUserByIdWithFeign")
    public ResponseEntity<UserDTO> getUserByIdWithRestTemplate(@PathVariable("id") Long id) {
    	logger.info("get user by ID using REST Template");
            UserDTO userDTO = userService.getUserByIdWithRestTemplate(id);
            if (userDTO == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/getUserFeign/{id}")
    
    @CircuitBreaker(name = "userServiceCircuitBreaker", fallbackMethod = "fallbackGetUserByIdWithFeign")
    public ResponseEntity<UserDTO> getUserByIdWithFeign(@PathVariable("id") Long id) {
        logger.info("Getting user by ID using FEIGN Client");
        
            UserDTO userDTO = userService.getUserByIdWithFeignClient(id);
            if (userDTO == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
    
    public ResponseEntity<UserDTO> getUserByIdWithFeignFallBack(Long id, Throwable t) {
        // Log the exception (optional)
        logger.error("Fallback triggered for user ID: {}. Error: {}", id, t.getMessage(), t);
        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setName("fallback");
        user.setEmail("fallback@gamil.com");
        // Return an appropriate fallback response
        return ResponseEntity.status(404).body(user);  // or you can return a default UserDTO or error message
    }


}

