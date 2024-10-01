package com.gontijo.user.controller;

import com.gontijo.user.model.ProductResponsePayload;
import com.gontijo.user.model.User;
import com.gontijo.user.producer.UserProducer;
import com.gontijo.user.service.UserService;
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
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    private final UserProducer producer;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    @Operation(summary = "Busca um usuário por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = User.class)) }),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
            content = @Content) })
    public User createUser(
        @RequestBody User user
    ) {
        logger.info("Creating user: {}", user);
        producer.sendMessage("User created: " + user.getName());
        return service.createUser(user);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um usuário por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = User.class)) }),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
            content = @Content) })
    public User getUserById(
        @PathVariable Long id
    ) {
        logger.info("Fetching user with id: {}", id);
        return service.getUserById(id);
    }

    @GetMapping
    @Operation(summary = "Lista todos os usuários")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = User.class)) }) })
    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return service.getAllUsers();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um usuário existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
            content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = User.class)) }),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
            content = @Content) })
    public User updateUser(
        @PathVariable Long id,
        @RequestBody User user
    ) {
        logger.info("Updating user with id: {}", id);
        return service.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
            content = @Content) })
    public void deleteUser(
        @PathVariable Long id
    ) {
        logger.info("Deleting user with id: {}", id);
        producer.sendMessage(String.format("User [ ID: %d ] deleted", id));
        service.deleteUser(id);
    }

    @GetMapping("/{id}/products")
    @Operation(summary = "Lista produtos de um usuário")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de produtos do usuário",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = ProductResponsePayload.class))
            }
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado",
            content = @Content)
    })
    public List<ProductResponsePayload> getUserProducts(
        @PathVariable Long id
    ) {
        logger.info("Fetching products for user with id: {}", id);
        return service.getUserProducts(id);
    }
}
