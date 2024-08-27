package com.cybergrid.assignment;

import com.cybergrid.assignment.model.Product;
import com.cybergrid.assignment.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDatabase {
    private static final Logger log = LoggerFactory.getLogger(InitDatabase.class);

    @Autowired
    private ProductRepository repository;

    @Bean
    CommandLineRunner initProductDatabase(ProductRepository repository) {
        return args -> log.info("Preloading " + repository.save(Product.builder().id(1).name("Casio A1000").price(5999).currency("EUR").build()));
    }

}
