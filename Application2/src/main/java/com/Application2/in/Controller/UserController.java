package com.Application2.in.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Application2.in.util.Constants;
import com.Application2.in.Client.UserFeignClient;
import com.Application2.in.DTO.UserDTO;
import com.Application2.in.Service.UserService;


@RestController
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(Constants.LOGGER_NAME_CONTROLLER);

    @Autowired
    private UserService userService;
 
    @Autowired
    private UserFeignClient userFeignClient;
    
    @PostMapping("/addUser")
    public String addUserToFirstApplication(@RequestBody UserDTO userDTO) {
        // Call the service method to add the user to the first application
        userService.addUserToFirstApplication(userDTO);
        logger.info("Added user successfully");
        return "User added to the first application successfully.";
    }
    
    
    @GetMapping("/getUserRestTemplate/{id}")
    public String getUserByIdWithRestTemplate(@PathVariable("id") Long id) {
    	logger.info("get user by ID using REST Template");
        return userService.getUserByIdWithRestTemplate(id);
    }

    @GetMapping("/getUserFeign/{id}")
    public String getUserByIdWithFeign(@PathVariable("id") Long id) {
    	logger.info("get user by ID using FEIGN Client");
        return userFeignClient.getUserById(id).toString();
    }
}

