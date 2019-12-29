package com.spring01.reviews.controller;

import com.spring01.reviews.model.Product;
import com.spring01.reviews.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with product entity.
 */
@RestController
@RequestMapping("/products")
@Validated
public class ProductsController {

    // TODO: Wire JPA repositories here
    @Autowired
    private ProductService productService;

    public void setProductService() {}

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Creates a product.
     *
     * 1. Accept product as argument. Use {@link RequestBody} annotation.
     * 2. Save product.
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product prod) {
        Optional<Product> product = Optional.ofNullable(productService.save(prod));
        if(product.isPresent()){
            return new ResponseEntity<Product>(product.get(), HttpStatus.CREATED);
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Product already exists");
    }

    /**
     * Finds a product by id.
     *
     * @param id The id of the product.
     * @return The product if found, or a 404 not found.
     */
    @RequestMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * Lists all products.
     *
     * @return The list of products.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<?> listProducts() {
        throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }
}