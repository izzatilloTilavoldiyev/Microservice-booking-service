package uz.pdp.bookingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import uz.pdp.bookingservice.entity.User;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TestUserController {

    private final RestTemplate restTemplate;

    @Value("${user-service.url}")
    private String userServiceUrl;

    @GetMapping("/test")
    public String testMethod() {
        return restTemplate.getForObject(userServiceUrl + "/hello", String.class);
    }

    @GetMapping("/user/{userId}")
    public User getUserFromUserService(
            @PathVariable UUID userId
    ) {
        return restTemplate.getForObject(
                userServiceUrl + "/get/" + userId, User.class);
    }

}