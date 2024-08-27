package com.cybergrid.assignment.mapper;

import com.cybergrid.assignment.dto.ProductCreationDto;
import com.cybergrid.assignment.dto.ProductDto;
import com.cybergrid.assignment.model.Product;


public class ProductMapper {

    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .formattedPrice(String.format("%.2f %s", (float) (product.getPrice() / 100), product.getCurrency()))
                .build();
    }

    public Product toProduct(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .currency(productDto.getCurrency())
                .build();
    }

    public Product toProduct(ProductCreationDto productCreationDto) {
        return Product.builder()
                .name(productCreationDto.getName())
                .description(productCreationDto.getDescription())
                .price(productCreationDto.getPrice())
                .currency(productCreationDto.getCurrency())
                .build();
    }

}
