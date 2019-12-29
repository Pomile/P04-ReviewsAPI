package com.spring01.reviews.controller;

import com.spring01.reviews.model.Product;
import com.spring01.reviews.service.ProductService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ProductsControllerTest {

    @Autowired private MockMvc mvc;
    @Autowired private JacksonTester<Product> json;
    @MockBean private ProductService productService;


    @Before
    public void setup() throws URISyntaxException {
        Product product = new Product();
        Product product1 = new Product();
        product.setId(1L);
        product1.setPrice(300.00);
        product1.setStock(50);
        List<Product> products = new ArrayList<>();
        products.add(product);
        given(productService.findById(1)).willReturn(java.util.Optional.of(product));
        given(productService.save(any())).willReturn(product);


    }
    @Test
    public void shouldCreateProduct() throws Exception {
        Product product = product();
        mvc.perform(post(new URI("/products"))
                .content(json.write(product).getJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void shouldNotCreateWithEmptyName() throws Exception {
        Product product = product();
        product.setName("");
        mvc.perform(post(new URI("/products"))
                .content(json.write(product).getJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors[0].name")
                        .value("product name cannot be empty"));;
    }

    @Test
    public void shouldNotCreateWithEmptyImage() throws Exception {
        Product product = product();
        product.setImage("");
        mvc.perform(post(new URI("/products"))
                .content(json.write(product).getJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors[0].image")
                        .value("image url cannot be empty"));;
    }

    @Test
    public void shouldNotCreateWithNullProductCode() throws Exception {
        Product product = product();
        product.setProductCode("");
        mvc.perform(post(new URI("/products"))
                .content(json.write(product).getJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error")
                        .value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors[0].productcode")
                        .value("product code cannot be empty"));
    }

    @Test
    public void shouldNotCreateIfPriceBelow1() throws Exception {
        Product product = product();
        product.setPrice(0.00);
        mvc.perform(post(new URI("/products"))
                .content(json.write(product).getJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors[0].price")
                        .value("price must be greater than or equal to 1"));
    }

    @Test
    public void shouldNotCreateIfStockIsBelow5() throws Exception {
        Product product = product();
        product.setStock(4);
        mvc.perform(post(new URI("/products"))
                .content(json.write(product).getJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.errors[0].stock")
                        .value("stock must be greater than or equal to 1.0"));
    }

    @Test
    public void shouldReturnAProduct() throws Exception {

        mvc.perform(get(new URI("/products/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void shouldReturnClientErrorIfIfProductIdIsZero() throws Exception {

        mvc.perform(get(new URI("/products/0"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors.[0].0").value("Invalid id"));
    }

    @Test
    public void shoulReturnClientErrorIfProductIdIsNegative() throws Exception {

        mvc.perform(get(new URI("/products/-1"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.error").value("BAD_REQUEST"));
    }

    @Test
    public void shoulReturnNotFoundIfProductIdDoesNotExist() throws Exception {

        mvc.perform(get(new URI("/products/20"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errors[0]").value("Product not found"));
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