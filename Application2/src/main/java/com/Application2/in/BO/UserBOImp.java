package com.Application2.in.BO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.Application2.in.DTO.UserDTO;
import com.Application2.in.RestTemplate.UserRestTemplate;
import com.Application2.in.RestTemplate.UserRestTemplateImp;
import com.Application2.in.util.Constants;

@Service
public class UserBOImp implements UserBO 
{
	private static final Logger logger = LoggerFactory.getLogger(Constants.LOGGER_NAME_BO);

//    @Autowired
//    private RestTemplate restTemplate;
	@Autowired
	private UserRestTemplate userRest;
	
    @Override
    public UserDTO getUserByIdWithRestTemplate(Long id) {
//    	  String url = "http://localhost:9090/api/users/id/";
//    	  
//        logger.info("Calling first application (RestTemplate) with ID: {}", id);
//        ResponseEntity<String> response = restTemplate.getForEntity(url + id, String.class);
//        if (response.getStatusCode().is2xxSuccessful()) {
//            logger.info("User found: {}", response.getBody());
//            return response.getBody();
//        } else {
//            logger.error("Failed to retrieve user with ID: {}", id);
//            return "User not found";
//        }
    	return userRest.getUserByIdWithRestTemplate(id);
    	
    	
    }
}
