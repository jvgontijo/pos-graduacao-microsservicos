package com.gontijo.user.service;

import com.gontijo.user.model.ProductResponsePayload;
import com.gontijo.user.model.User;
import com.gontijo.user.repository.UserRepository;
import com.gontijo.user.service.client.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final RestTemplate restTemplate;

    private final ProductClient productClient;

    public User createUser(User user) {
        return repository.save(user);
    }

    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(() ->
            new RuntimeException(String.format("User [ ID: %d ] not found!", id))
        );
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            return repository.save(existingUser);
        }
        return null;
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public List<ProductResponsePayload> getUserProducts(Long userId) {
        String url = "http://localhost:8081/products/user/" + userId;
        return restTemplate.getForObject(url, List.class);
    }
}
