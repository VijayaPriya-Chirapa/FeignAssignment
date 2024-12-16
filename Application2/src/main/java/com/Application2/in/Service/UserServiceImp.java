package com.Application2.in.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Application2.in.BO.UserBOImp;
import com.Application2.in.Client.UserFeignClient;
import com.Application2.in.DTO.UserDTO;
import com.Application2.in.util.Constants;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImp implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(Constants.LOGGER_NAME_SERVICE);

    @Autowired
    private UserFeignClient userfeign;

    @Autowired
    private UserBOImp userBO;
    
    
   
    @Override
    @CircuitBreaker(name = "userServiceCircuitBreaker", fallbackMethod = "fallbackGetUserByIdWithFeign")
    public UserDTO getUserByIdWithRestTemplate(Long id) {
        logger.info("Getting user by ID with REST Template");
        return userBO.getUserByIdWithRestTemplate(id); // Let the exception propagate, circuit breaker will handle it
    }

    @Override
    @CircuitBreaker(name = "userServiceCircuitBreaker", fallbackMethod = "fallbackGetUserByIdWithFeign")
    public UserDTO getUserByIdWithFeignClient(Long id) {
        logger.info("Getting user by ID using Feign Client");
        return userfeign.getUserById(id); // Feign client call, let exceptions propagate for fallback to trigger
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
