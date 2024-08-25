package com.cybergrid.assignment.controller;

import com.cybergrid.assignment.model.Product;
import com.cybergrid.assignment.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@SuppressWarnings("unused")
@RequestMapping("/api")
class ProductController {

    private final ProductRepository repository;

    ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    /**
     * Find all products stored in the database
     *
     * @return List of all existing products
     */
    @GetMapping("/products")
    ResponseEntity<List<Product>> all() {
        return ResponseEntity.ok(repository.findAll());
    }

    /**
     * Creates a new product
     *
     * @param newProduct - product you wish to create
     * @return The newly created product
     */
    @PostMapping("/products")
    ResponseEntity<Product> createProduct(@RequestBody Product newProduct) {
        Optional<Product> createdProduct = repository.saveReturnOptional(newProduct);
        return createdProduct.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.unprocessableEntity().build());
    }

    /**
     * Find a product using an ID
     *
     * @param id function will try to find a product with a matching ID
     * @return The found product
     * @throws EntityNotFoundException if the product could not be found
     */
    @GetMapping("/products/{id}")
    ResponseEntity<Product> findById(@PathVariable Integer id) {
        return repository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Find a product using an ID
     *
     * @param name function will try to find all products with a matching name
     * @return The found product
     * @throws EntityNotFoundException if the product could not be found
     */
    @GetMapping("/products/{id}")
    ResponseEntity<List<Product>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(repository.findByName(name));
    }

    /**
     * Replace an existing product.
     *
     * @param newProduct new product given in response body
     * @param id         ID of product that should be replaced
     * @return Returns newly created product if product was replaced successfully. Else returns null with code 404
     */
    @PutMapping("/products/{id}")
    ResponseEntity<Product> replaceProduct(@RequestBody Product newProduct, @PathVariable Integer id) {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            Product foundProduct = optionalProduct.get();
            foundProduct.setCurrency(newProduct.getCurrency());
            foundProduct.setPrice(newProduct.getPrice());
            foundProduct.setName(newProduct.getName());
            foundProduct.setDescription(newProduct.getDescription());
            return new ResponseEntity<>(repository.save(foundProduct), HttpStatusCode.valueOf(200));
        } else {
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
        }
    }

    /**
     * Delete the product with a matching ID
     *
     * @param id ID of the product to be deleted
     */
    @DeleteMapping("/products/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}