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
import com.Application2.in.util.Constants;

@Service
public class UserBOImp implements UserBO 
{
	private static final Logger logger = LoggerFactory.getLogger(Constants.LOGGER_NAME_BO);

    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://localhost:8080/api/users/id/";

    @Override
    public void addUserToFirstApplication(UserDTO userDTO) 
    {
        String url = "http://localhost:8080/api/users/add"; // First application's endpoint

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<UserDTO> request = new HttpEntity<>(userDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
    }


    @Override
    public String getUserByIdWithRestTemplate(Long id) {
        logger.info("Calling first application (RestTemplate) with ID: {}", id);
        ResponseEntity<String> response = restTemplate.getForEntity(url + id, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            logger.info("User found: {}", response.getBody());
            return response.getBody();
        } else {
            logger.error("Failed to retrieve user with ID: {}", id);
            return "User not found";
        }
    }
}
