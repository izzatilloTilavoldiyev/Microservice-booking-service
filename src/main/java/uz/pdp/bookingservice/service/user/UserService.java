package uz.pdp.bookingservice.service.user;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import uz.pdp.bookingservice.entity.User;
import uz.pdp.bookingservice.exception.DataNotFoundException;
import uz.pdp.bookingservice.exception.DuplicateDataException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RestTemplate restTemplate;

    @Value("${user-service.url}")
    private String userServiceUrl;

    public User getUserById(UUID userID) {
        try {
            return restTemplate.getForObject(userServiceUrl + "/get/"
                    + userID, User.class);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new DataNotFoundException("User not found with ID: " + userID);
        }
    }

    public void checkUserExists(UUID userID) {
        User user = getUserById(userID);
        if (user == null)
            throw new DuplicateDataException("User not found with ID: " + userID);
    }
}