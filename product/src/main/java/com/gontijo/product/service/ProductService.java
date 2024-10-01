package com.gontijo.product.service;

import com.gontijo.product.model.Product;
import com.gontijo.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Product createProduct(Product product) {
        return repository.save(product);
    }

    public Product getProductById(Long id) {
        return repository.findById(id).orElseThrow(() ->
            new RuntimeException(String.format("Product [ ID: %d ] not found!", id))
        );
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = getProductById(id);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            return repository.save(existingProduct);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }

    public List<Product> getProductsByUserId(Long userId) {
        return repository.findByUserId(userId);
    }



}
