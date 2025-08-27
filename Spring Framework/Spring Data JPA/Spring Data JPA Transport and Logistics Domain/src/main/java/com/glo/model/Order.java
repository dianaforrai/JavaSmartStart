package com.glo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "order_totalamount", nullable = false)
    private BigDecimal orderTotalAmount;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_id", referencedColumnName = "cust_id")
    private Customer customer;

    @Column(name = "order_product_quantity", nullable = false)
    private Integer orderProductQuantity;

    // Constructors
    public Order() {}

    public Order(BigDecimal orderTotalAmount, String orderStatus, Product product,
                 Customer customer, Integer orderProductQuantity) {
        this.orderTotalAmount = orderTotalAmount;
        this.orderStatus = orderStatus;
        this.product = product;
        this.customer = customer;
        this.orderProductQuantity = orderProductQuantity;
    }

    // Getters and Setters
    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }

    public BigDecimal getOrderTotalAmount() { return orderTotalAmount; }
    public void setOrderTotalAmount(BigDecimal orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Integer getOrderProductQuantity() { return orderProductQuantity; }
    public void setOrderProductQuantity(Integer orderProductQuantity) {
        this.orderProductQuantity = orderProductQuantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderTotalAmount=" + orderTotalAmount +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderProductQuantity=" + orderProductQuantity +
                '}';
    }
}