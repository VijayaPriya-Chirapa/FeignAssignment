package com.Application2.in.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Application2.in.BO.UserBOImp;
import com.Application2.in.DTO.UserDTO;
import com.Application2.in.util.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImp implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(Constants.LOGGER_NAME_SERVICE);
    
    @Autowired
    private UserBOImp userBO;

    @Override
    public void addUserToFirstApplication(UserDTO userDTO)
    {
    	logger.info("Added User");
    	userBO.addUserToFirstApplication(userDTO);
     }
    
    @Override
    public String getUserByIdWithRestTemplate(Long id) 
    {
    	logger.info("getting user by ID");
    	return userBO.getUserByIdWithRestTemplate(id);
    }
    
    

}


