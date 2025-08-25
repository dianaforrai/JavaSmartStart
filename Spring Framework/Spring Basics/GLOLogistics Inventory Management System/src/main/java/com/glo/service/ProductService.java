package com.glo.service;

import com.glo.model.Product;
import com.glo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        logger.info("Adding new product: {}", product.getProductName());
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        logger.info("Updating product with ID: {}", product.getProductId());
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        logger.info("Deleting product with ID: {}", productId);
        productRepository.deleteById(productId);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    public List<Product> viewAllProducts() {
        List<Product> products = productRepository.findAll();
        products.forEach(System.out::println);
        return products;
    }

    public Product orderProduct(Long productId) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            logger.info("Viewing product for ordering: {}", product);
            return product;
        }
        return null;
    }
}