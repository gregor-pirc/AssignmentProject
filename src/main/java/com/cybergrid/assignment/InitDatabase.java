package com.cybergrid.assignment;

import com.cybergrid.assignment.model.Product;
import com.cybergrid.assignment.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDatabase {
    private static final Logger log = LoggerFactory.getLogger(InitDatabase.class);

    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> log.info("Preloading " + repository.save(Product.builder().name("Casio A1000").price(5999).currency("EUR").build()));
    }
}
