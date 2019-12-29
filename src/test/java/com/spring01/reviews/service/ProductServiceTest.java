package com.spring01.reviews.service;

import com.spring01.reviews.model.Product;
import com.spring01.reviews.repository.ProductRepository;
import org.junit.Before;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ProductService.class})
public class ProductServiceTest {

    @MockBean private ProductRepository productRepository;
    @Autowired private ProductService productService;

    @Before
    public void setup(){ }
    @Test
    public void save() {
        Product product = product();
        product.setId(1L);
        Mockito.when(
                productRepository.save(any()))
                .thenReturn(product);

        Optional<Product> product1 = Optional.ofNullable(productService.save(product));
        assertEquals("Blue band", product1.get().getName());
        assertEquals(1L, product1.get().getId());
    }

    private Product product(){
        Product product = new Product();
        product.setName("Blue band");
        product.setDescription("Nutritious Margarine");
        product.setPrice(700.00);
        product.setStock(20);
        product.setRatings(0);
        product.setImage("blueBand.jpg");
        product.setProductCode("44344AB");

        return  product;
    }
}