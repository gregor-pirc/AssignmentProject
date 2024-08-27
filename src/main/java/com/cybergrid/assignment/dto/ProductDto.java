package com.cybergrid.assignment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO that is returned by GET requests and should be used by the client.
 * Includes formatted price (@see @link com.cybergrid.assignment.mapper.ProductMapper.class)
 */
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
