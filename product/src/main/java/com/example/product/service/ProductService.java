package com.example.product.service;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    protected final ProductRepository repository;

    public List<Product> getAllByUsername(String username)
    {
        return repository.findAllByProductOwnerUsername(username);
    }
    public void createProduct(String authenticatedUser, Product productToCreate){
        Product newProduct = new Product();
        newProduct.setProductName(productToCreate.getProductName());
        newProduct.setProductDescription(productToCreate.getProductDescription());
        newProduct.setProductOwnerUsername(authenticatedUser);
        newProduct.setCost(productToCreate.getCost());
        repository.save(newProduct);
    }
    public void updateProduct(String authenticatedUser, Product productToUpdate) throws Exception {
        Optional<Product> discoveredProduct = repository.findById(productToUpdate.getId());

        if(discoveredProduct.isPresent())
        {
            Product updateProduct = discoveredProduct.get();
            updateProduct.setProductName(productToUpdate.getProductName());
            updateProduct.setProductDescription(productToUpdate.getProductDescription());
            updateProduct.setProductOwnerUsername(authenticatedUser);
            updateProduct.setCost(productToUpdate.getCost());
            repository.save(updateProduct);

        }
        else
        {
            throw new Exception(String.format("Product with id %s and username %s was not found in db.",productToUpdate.getId(),authenticatedUser));
        }
    }

    @Transactional()
    public void deleteProduct(String authenticatedUser, Long productId) throws Exception{
        if( this.repository.deleteByProductOwnerUsernameAndId(authenticatedUser,productId) == 0)
        {
            throw new Exception(String.format("Product with id %d and username %s was not found in the db.",productId,authenticatedUser));
        }
    }
}
