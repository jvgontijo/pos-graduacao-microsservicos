package com.gontijo.product.controller;

import com.gontijo.product.model.Product;
import com.gontijo.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @PostMapping
    @Operation(summary = "Cria um novo produto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Produto criado com sucesso",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Product.class)) }),
        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    public Product createProduct(
        @RequestBody Product product
    ) {
        logger.info("Creating product: {}", product);
        return service.createProduct(product);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um produto por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto encontrado",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Product.class)) }),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    })
    public Product getProductById(
        @PathVariable Long id
    ) {
        logger.info("Fetching product with id: {}", id);
        return service.getProductById(id);
    }

    @GetMapping
    @Operation(summary = "Lista todos os produtos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Product.class)) })
    })
    public List<Product> getAllProducts() {
        logger.info("Fetching all products");
        return service.getAllProducts();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um produto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Product.class)) }),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content),
        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    public Product updateProduct(
        @PathVariable Long id,
        @RequestBody Product product
    ) {
        logger.info("Updating product with id: {}", id);
        return service.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um produto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    })
    public void deleteProduct(
        @PathVariable Long id
    ) {
        logger.info("Deleting product with id: {}", id);
        service.deleteProduct(id);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Busca produtos por ID do usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produtos encontrados para o usuário",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = Product.class)) }),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    public List<Product> getProductsByUserId(
        @PathVariable Long userId
    ) {
        logger.info("Fetching products for user with id: {}", userId);
        return service.getProductsByUserId(userId);
    }
}
