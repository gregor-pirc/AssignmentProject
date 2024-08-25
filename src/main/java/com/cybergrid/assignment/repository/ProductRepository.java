package com.cybergrid.assignment.repository;

import com.cybergrid.assignment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    default Optional<Product> saveReturnOptional(Product product) {
        return Optional.of(save(product));
    }

    List<Product> findByName(String name);

}