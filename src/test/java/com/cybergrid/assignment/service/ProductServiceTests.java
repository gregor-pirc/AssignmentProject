package com.cybergrid.assignment.service;

import com.cybergrid.assignment.controller.ProductController;
import com.cybergrid.assignment.dto.ProductCreationDto;
import com.cybergrid.assignment.dto.ProductDto;
import com.cybergrid.assignment.mapper.ProductMapper;
import com.cybergrid.assignment.model.Product;
import com.cybergrid.assignment.repository.ProductRepository;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductServiceTests {
    private final ProductDto productDto = ProductDto.builder().name("Milk").price(100).currency("EUR").formattedPrice("1.00 EUR").build();
    private final Product product = Product.builder().name("Milk").price(100).currency("EUR").build();

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductController productController;
    @Mock
    private ProductMapper mapper;
    @Captor
    private ArgumentCaptor<Product> productCaptor;
    @Captor
    private ArgumentCaptor<ProductDto> productDtoCaptor;
    @Captor
    private ArgumentCaptor<ProductCreationDto> productCreationDtoCaptor;

    @Nested
    @DisplayName("Tests of ProductService's findById method.")
    class FindByIDTest {
        @Test
        void testReturnsProduct() {
            when(productController.findById(anyInt())).thenReturn(ResponseEntity.of(Optional.of(productDto)));
            val mockedProduct = productRepository.findById(anyInt()).orElseThrow();
            verify(mapper, times(1)).toDto(productCaptor.capture());
            assertEquals(productCaptor.getValue(), product);
            assertEquals(mockedProduct.getName(), product.getName());
            assertEquals(mockedProduct.getCurrency(), product.getCurrency());
            assertEquals(mockedProduct.getPrice(), product.getPrice());
        }

        @Test
        void IfNoUserFound() {
            val optionalProductDto = productRepository.findById(anyInt());
            assertTrue(optionalProductDto.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests of ProductService's findAll method.")
    class FindAllTest {
        @Test
        void testReturnsListOfProductDtos() {
            when(productController.getAll()).thenReturn(ResponseEntity.ok(List.of(productDto)));
            val list = productRepository.findAll(PageRequest.of(0, 20, Sort.by("id")));
            assertThat(list).containsOnly(Product.builder().build());
        }
    }

    @Nested
    @DisplayName("Tests of ProductService's create method.")
    class SaveTest {
        @Test
        void testServiceSavesProduct() {
            productRepository.save(product);
            verify(productController, times(1)).createProduct(productCreationDtoCaptor.capture());
            Product tempProduct = productCaptor.getValue();

            verify(mapper, times(1)).toProduct(productDtoCaptor.capture());
            assertEquals(productDtoCaptor.getValue(), productDto);

            assertEquals(tempProduct.getName(), productDto.getName());
            assertEquals(tempProduct.getCurrency(), productDto.getCurrency());
            assertEquals(tempProduct.getPrice(), productDto.getPrice());
        }
    }
}
