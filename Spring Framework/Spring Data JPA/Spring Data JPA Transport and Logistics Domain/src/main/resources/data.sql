-- Insert sample products
INSERT INTO product (product_name, product_price, product_quantity_instock) VALUES
('Laptop Dell XPS 13', 1299.99, 50),
('Mouse Logitech MX Master', 99.99, 200),
('Keyboard Mechanical RGB', 149.99, 75),
('Monitor Samsung 27"', 299.99, 30),
('Headphones Sony WH-1000XM4', 349.99, 100);

-- Insert sample customers
INSERT INTO customer (cust_name, cust_contact, cust_add) VALUES
('John Doe', 'john.doe@email.com', '123 Main St, City, State'),
('Jane Smith', 'jane.smith@email.com', '456 Oak Ave, City, State'),
('Bob Johnson', 'bob.johnson@email.com', '789 Pine Rd, City, State'),
('Alice Brown', 'alice.brown@email.com', '321 Elm St, City, State');

-- Insert sample orders
INSERT INTO orders (order_totalamount, order_status, product_id, cust_id, order_product_quantity) VALUES
(1299.99, 'shipped', 1, 1, 1),
(199.98, 'pending', 2, 2, 2),
(299.99, 'delivered', 4, 3, 1),
(499.97, 'processing', 3, 4, 3);