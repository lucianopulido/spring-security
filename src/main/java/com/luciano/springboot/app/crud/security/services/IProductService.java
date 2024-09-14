package com.luciano.springboot.app.crud.security.services;

import java.util.List;
import java.util.Optional;

import com.luciano.springboot.app.crud.security.entities.Product;

public interface IProductService {
    
    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);
    
    Optional<Product> update(Long id, Product product);

    Optional<Product> delete(Long id);

    boolean existsBySku(String sku);
}
