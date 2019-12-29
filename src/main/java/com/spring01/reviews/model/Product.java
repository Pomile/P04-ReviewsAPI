package com.spring01.reviews.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @NotNull(message = "product name field must be provided")
    @NotBlank(message = "product name cannot be empty")
    private String name;

    @NotNull(message = "image field must be provided")
    @NotBlank(message = "image url cannot be empty")
    private String image;

    @NotNull(message = "description field must be provided")
    @NotBlank(message = "description cannot be empty")
    private String description;

    @Min(value = 1, message = "price must be greater than or equal to 1")
    @NotNull(message = "price field must be provided")
    private Double price;

    @Min(value = 5, message = "stock must be greater than or equal to 1.0")
    @NotNull(message = "stock must be provided")
    private Integer stock;

    @NotNull(message = "product code must be provided")
    @NotBlank(message = "product code cannot be empty")
    private String productcode;

    private Integer ratings = 0;

    public Product() {}

    public Product(String name,
                   String image,
                   String description,
                   Double price,
                   Integer stock,
                   String productCode) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.productcode=productCode;
    }

    public Long getId() {
        return productId;
    }

    public void setId(Long id) {
        this.productId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getProductCode() {
        return productcode;
    }

    public void setProductCode(String productCode) {
        this.productcode = productCode;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", createdTime=" + productcode +
                '}';
    }

    public Integer getRatings() {
        return ratings;
    }

    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }
}