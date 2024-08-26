package com.cybergrid.assignment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDto {

    private int id;
    private String name;
    private String description;
    private Integer price;
    private String currency;
    private String formattedPrice;

}
