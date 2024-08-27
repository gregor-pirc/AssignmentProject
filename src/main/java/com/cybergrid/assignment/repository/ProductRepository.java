package com.cybergrid.assignment.repository;

import com.cybergrid.assignment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * Custom find function. The code is automatically generated using the name of the function (findBy____)
     * <p>
     * See guide on creating <a href="https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html">Query Methods</a>
     *
     * @param name the value we are looking for
     * @return List of products with the matching name, empty if none can be found
     */
    List<Product> findByName(String name);

}