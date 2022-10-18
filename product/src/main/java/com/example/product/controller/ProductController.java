package com.example.product.controller;

import com.example.product.model.Product;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    protected final ProductService service;

    @GetMapping("/all/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll(@PathVariable("username") String username ){
        return this.service.getAllByUsername(username);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void post(@CookieValue("CUSTOMER") String authenticatedUser, @RequestBody Product productToPost)
    {
        service.createProduct(authenticatedUser,productToPost);
    };

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void put(@CookieValue("CUSTOMER") String authenticatedUser , @RequestBody Product productToPost) throws Exception {
        service.updateProduct(authenticatedUser,productToPost);
    };
    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@CookieValue("CUSTOMER") String authenticatedUser, @PathVariable Long productId) throws Exception {
        service.deleteProduct(authenticatedUser,productId);
    };
}
