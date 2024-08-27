package com.cybergrid.assignment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO that is used for POST requests, when the ID value is not needed
 */
@Builder
@Getter
@Setter
public class ProductCreationDto {

    private String name;
    private String description;
    private Integer price;
    private String currency;

}
