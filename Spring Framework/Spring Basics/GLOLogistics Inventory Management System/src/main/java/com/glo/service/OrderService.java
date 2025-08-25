package com.glo.service;

import com.glo.model.Order;
import com.glo.model.Product;
import com.glo.model.Customer;
import com.glo.repository.OrderRepository;
import com.glo.repository.ProductRepository;
import com.glo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Order generateOrder(Long productId, Long customerId, Integer quantity) {
        logger.info("Generating order for product ID: {} and customer ID: {}", productId, customerId);

        Optional<Product> productOpt = productRepository.findById(productId);
        Optional<Customer> customerOpt = customerRepository.findById(customerId);

        if (productOpt.isPresent() && customerOpt.isPresent()) {
            Product product = productOpt.get();
            Customer customer = customerOpt.get();

            if (product.getProductQuantityInStock() >= quantity) {
                Order order = new Order(
                        LocalDateTime.now(),
                        "PENDING",
                        quantity,
                        product,
                        customer
                );

                // Update product stock
                product.setProductQuantityInStock(product.getProductQuantityInStock() - quantity);
                productRepository.save(product);

                return orderRepository.save(order);
            } else {
                logger.warn("Insufficient stock for product: {}", product.getProductName());
                throw new RuntimeException("Insufficient stock");
            }
        }

        throw new RuntimeException("Product or Customer not found");
    }

    public Order updateOrder(Long orderId, String status) {
        logger.info("Updating order ID: {} to status: {}", orderId, status);

        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setOrderStatus(status);
            return orderRepository.save(order);
        }

        throw new RuntimeException("Order not found");
    }

    public void deleteOrder(Long orderId) {
        logger.info("Deleting order with ID: {}", orderId);
        orderRepository.deleteById(orderId);
    }

    public List<Order> viewAllOrders() {
        List<Order> orders = orderRepository.findAll();
        orders.forEach(System.out::println);
        return orders;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }
}