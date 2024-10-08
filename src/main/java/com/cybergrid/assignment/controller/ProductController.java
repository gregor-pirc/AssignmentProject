package com.cybergrid.assignment.controller;

import com.cybergrid.assignment.dto.ProductCreationDto;
import com.cybergrid.assignment.dto.ProductDto;
import com.cybergrid.assignment.mapper.ProductMapper;
import com.cybergrid.assignment.model.Product;
import com.cybergrid.assignment.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@SuppressWarnings("unused")
@RequestMapping("/api")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
        this.mapper = new ProductMapper();
    }

    /**
     * Find all products stored in the database
     *
     * @return List of all existing products
     */
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList()));
    }

    /**
     * Creates a new product
     *
     * @param newProduct - product you wish to create, request body should fit {@link ProductCreationDto} schema
     * @return The newly created product
     */
    @PostMapping("/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductCreationDto newProduct) {
        return Optional.of(repository.save(mapper.toProduct(newProduct)))
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Find a product using an ID
     *
     * @param id function will try to find a product with a matching ID
     * @return The found product, in the form of {@link ProductDto}
     * @throws EntityNotFoundException if the product could not be found
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Find all products that have a name matching the given String
     *
     * @param name function will try to find all products with a matching name
     * @return List<ProductDto> of the found products, empty of no product could be found or if error thrown
     */
    @GetMapping("/products/name/{name}")
    public ResponseEntity<List<ProductDto>> findByName(@PathVariable String name) {
        return ResponseEntity.ok(repository.findByName(name).stream().map(mapper::toDto).collect(Collectors.toList()));
    }

    /**
     * Replace an existing product.
     *
     * @param newProduct new product given in response body
     * @param id         ID of product that should be replaced
     * @return Returns newly created product if product was replaced successfully. Else returns null with code 404
     */
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> replaceProduct(@RequestBody ProductCreationDto newProduct, @PathVariable Integer id) {
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
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * @param e exception that was intercepted
     * @return ResponseEntity containing the error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleResourceNotFoundException(Exception e) {
        log.error("An exception was thrown:", e);
        return new ResponseEntity<>("An error occurred while handling your request: " + e.getMessage(), HttpStatusCode.valueOf(404));
    }

}