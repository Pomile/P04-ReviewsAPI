package com.spring01.reviews.service;

import com.spring01.reviews.model.Product;
import com.spring01.reviews.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * Implements the product service create, read, update or delete
 * information about products.
 */
@Service
public class ProductService{
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     ** creates a Product
     * @param product A product object, which should be new
     * @return the new product is stored in the repository
     */
    public Product save(Product product){
        return productRepository.save(product);
    }

    /**
     ** find a Product by Id
     * @param id an integer
     * @return a product
     */
    public Optional<Product> findById(Integer id){
        return productRepository.findById((Long.valueOf(id)));
    }

    /**
     ** find a Product by Id
     * @param limit an integer
     * @param offset an integer
     * @return a product
     */
    public Page<Product> findAll(Integer offset, Integer limit){
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Product> products=  productRepository.findAll(pageable);
        return products;
    }

}