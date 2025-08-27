package com.glo.service;

import com.glo.model.Order;
import com.glo.model.Product;
import com.glo.model.Customer;
import com.glo.repository.OrderRepository;
import com.glo.repository.ProductRepository;
import com.glo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Order a product (US006)
    public com.glo.model.Order orderProduct(Integer productId, Integer customerId, Integer quantity) {
        Optional<Product> productOpt = productRepository.findById(productId);
        Optional<Customer> customerOpt = customerRepository.findById(customerId);

        if (productOpt.isPresent() && customerOpt.isPresent()) {
            Product product = productOpt.get();
            Customer customer = customerOpt.get();

            if (product.getProductQuantityInStock() >= quantity) {
                // Calculate total amount
                BigDecimal totalAmount = product.getProductPrice().multiply(new BigDecimal(quantity));

                // Create order
                Order order = new Order(totalAmount, "pending", product, customer, quantity);
                Order savedOrder = orderRepository.save(order);

                // Update product stock
                product.setProductQuantityInStock(product.getProductQuantityInStock() - quantity);
                productRepository.save(product);

                return savedOrder;
            } else {
                throw new RuntimeException("Insufficient stock for product: " + product.getProductName());
            }
        } else {
            throw new RuntimeException("Product or Customer not found");
        }
    }
    public List<com.glo.model.Order> viewAllOrdersByCustomer(Integer customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    // Delete order (US008)
    public void deleteOrder(Integer orderId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();

            // Restore product stock
            Product product = order.getProduct();
            product.setProductQuantityInStock(
                    product.getProductQuantityInStock() + order.getOrderProductQuantity()
            );
            productRepository.save(product);

            // Delete order
            orderRepository.deleteById(orderId);
        } else {
            throw new RuntimeException("Order not found with id: " + orderId);
        }
    }

    // Create order request (US009)
    public com.glo.model.Order generateOrder(Integer productId, Integer customerId, Integer quantity) {
        return orderProduct(productId, customerId, quantity);
    }

    // Update order status (US010)
    public com.glo.model.Order updateOrderStatus(Integer orderId, String status) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setOrderStatus(status);
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Order not found with id: " + orderId);
        }
    }

    // Delete order (US011)
    public void removeOrder(Integer orderId) {
        deleteOrder(orderId);
    }

    // Get all orders
    public List<com.glo.model.Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get order by ID
    public Optional<com.glo.model.Order> getOrderById(Integer orderId) {
        return orderRepository.findById(orderId);
    }
}
