package com.luciano.springboot.app.crud.security.repositories;

import org.springframework.data.repository.CrudRepository;

import com.luciano.springboot.app.crud.security.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    boolean existsBySku(String sku);
}
