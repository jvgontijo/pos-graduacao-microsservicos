package com.gontijo.product.service.client;

import com.gontijo.product.model.UserResponsePayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("USER")
public interface UserClient {

    @GetMapping("/users/{id}")
    UserResponsePayload getUser(@PathVariable Long id);
}
