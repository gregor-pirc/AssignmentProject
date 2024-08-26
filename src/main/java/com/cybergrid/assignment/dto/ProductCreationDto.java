package com.cybergrid.assignment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductCreationDto {

    private String name;
    private String description;
    private Integer price;
    private String currency;

}
