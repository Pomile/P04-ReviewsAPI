package com.spring01.reviews.repository;

import com.spring01.reviews.model.Product;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.core.JdbcTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import javax.persistence.EntityManager;
import javax.sql.DataSource;


import java.sql.*;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductRepositoryTest {
    private SessionFactory sessionFactory;
    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private TestEntityManager testEntityManager;
    @Autowired private ProductRepository productRepository;

    private Long productId;
    private Product product;
    private Optional<Product> actual;

    @Before
    public void init() throws SQLException {
        Connection con = jdbcTemplate.getDataSource().getConnection();
        System.err.println("Connected to" + con.getMetaData().getURL());
        Product product = product();
        this.product = product;
    }

    @Test
    public void a_injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(testEntityManager).isNotNull();
        assertThat(productRepository).isNotNull();
    }

    @Test
    public void b_saveProduct(){
        entityManager.persist(product);
        Optional<Product> regProd = productRepository.findByName("Blue band");
        this.productId = regProd.get().getId();
        this.actual = regProd;
        assertThat(actual).isNotNull();
        assertEquals(productId, actual.get().getId());
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