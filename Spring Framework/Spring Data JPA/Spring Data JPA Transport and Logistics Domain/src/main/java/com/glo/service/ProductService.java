package com.glo.service;

import com.glo.model.Product;
import com.glo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Add Product (US001)
    public com.glo.model.Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Update Product (US002)
    public Product updateProduct(Product product) {
        if (productRepository.existsById(product.getProductId())) {
            return productRepository.save(product);
        }
        throw new RuntimeException("Product not found with id: " + product.getProductId());
    }

    // Delete Product (US003)
    public void deleteProduct(Integer productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        } else {
            throw new RuntimeException("Product not found with id: " + productId);
        }
    }

    // Get All Products (US004, US005)
    public List<com.glo.model.Product> getAllProducts() {
        return productRepository.findAll();
    }

    // View All Products
    public List<com.glo.model.Product> viewAllProducts() {
        return productRepository.findAll();
    }

    // Get Product by ID
    public Optional<com.glo.model.Product> getProductById(Integer productId) {
        return productRepository.findById(productId);
    }

    // View freight by ID
    public com.glo.model.Product viewProductById(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
    }
}