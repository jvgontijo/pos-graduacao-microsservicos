package com.gontijo.user.service;

import com.gontijo.user.model.ProductResponsePayload;
import com.gontijo.user.service.client.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductClient client;

    public ProductResponsePayload getProductsByUserId(Long userId) {
        return client.getProducts(userId);
    }
}
