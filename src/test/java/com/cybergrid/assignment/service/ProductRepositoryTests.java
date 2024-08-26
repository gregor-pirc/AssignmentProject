package com.cybergrid.assignment.service;

import com.cybergrid.assignment.dto.ProductDto;
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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductRepositoryTests {
    private final ProductDto productDto = ProductDto.builder().name("Milk").price(100).currency("EUR").formattedPrice("1.00 EUR").build();
    private final Product product = Product.builder().name("Milk").price(100).currency("EUR").build();

    @Mock
    private ProductRepository productRepository;
    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @Nested
    @DisplayName("Tests of ProductRepository's findById method.")
    class FindByIDTest {
        @Test
        void testReturnsProduct() {
            when(productRepository.findById(anyInt())).thenReturn(Optional.ofNullable(product));

            Product mockedProduct = productRepository.findById(anyInt()).orElseThrow();

            assertEquals(mockedProduct, product);
            assertEquals(mockedProduct.getId(), product.getId());
        }

        @Test
        void ifNoProductFound() {
            val optionalProductDto = productRepository.findById(anyInt());
            assertTrue(optionalProductDto.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests of ProductRepository's findAll method.")
    class FindAllTest {
        @Test
        void testReturnsListOfProducts() {
            when(productRepository.findAll()).thenReturn(List.of(product));

            val list = productRepository.findAll();

            assertThat(list).containsOnly(product);
        }
    }

    @Nested
    @DisplayName("Tests of ProductRepository's create method.")
    class SaveTest {
        @Test
        void testServiceSavesProduct() {
            Mockito.when(productRepository.save(product)).thenReturn(product);

            productRepository.save(product);

            verify(productRepository, times(1)).save(productCaptor.capture());

            assertEquals(productCaptor.getValue().getName(), product.getName());
        }
    }
}
