package com.cybergrid.assignment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Definition of product entity for JPA library
 */
@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private Integer price;
    private String currency;

    private Product() {
    }

    /**
     * For testing and creating mock data
     *
     * @param name name of product
     */
    public Product(String name) {
        this.name = name;
    }

}
