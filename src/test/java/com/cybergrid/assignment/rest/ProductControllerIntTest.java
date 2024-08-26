package com.cybergrid.assignment.rest;


import com.cybergrid.assignment.controller.ProductController;
import com.cybergrid.assignment.dto.ProductCreationDto;
import com.cybergrid.assignment.dto.ProductDto;
import com.cybergrid.assignment.model.Product;
import com.cybergrid.assignment.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProductControllerIntTest {

    private final Product restProduct = Product.builder().name("Milk").description("").price(100).currency("EUR").build();
    private final ProductCreationDto creationDto = ProductCreationDto.builder().name("Milk").description("").price(100).currency("EUR").build();
    private final ProductDto productDto = ProductDto.builder().name("Milk").description("").price(100).currency("EUR").build();

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void postReturnsStatusOk() throws Exception {
        Mockito.when(productController.createProduct(creationDto)).thenReturn(ResponseEntity.ok(productDto));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(restProduct));

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    void getReturnsProduct() throws Exception {
        Mockito.when(productController.findById(anyInt())).thenReturn(ResponseEntity.ok(productDto));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(Charset.defaultCharset())
                .param("id", "1");

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }
}
