package com.gontijo.user.service.client;

import com.gontijo.user.model.ProductResponsePayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("PRODUCT")
public interface ProductClient {

    @GetMapping("/products")
    ProductResponsePayload getProducts(@RequestBody Long userId);
}
