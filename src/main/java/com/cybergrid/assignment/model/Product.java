package com.cybergrid.assignment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Definition of product entity for JPA library, will be automatically linked with the database table "products"
 */
@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@AllArgsConstructor
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

}
