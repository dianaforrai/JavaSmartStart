package com.globallogic.hitachi.service;

import com.globallogic.hitachi.entity.Customer;
import com.globallogic.hitachi.exception.CustomerDoesNotExistException;
import com.globallogic.hitachi.exception.CustomerTableEmptyException;
import com.globallogic.hitachi.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerService {

    public List<Customer> getCustomersInBangalore() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        List<Customer> customers = null;

        try {
            transaction = session.beginTransaction();

            // Using Hibernate to fetch all customers
            Query<Customer> query = session.createQuery("FROM Customer", Customer.class);
            List<Customer> allCustomers = query.getResultList();

            // Using Java 8 Streams to filter customers in Bangalore
            customers = allCustomers.stream()
                    .filter(customer -> "Bangalore".equalsIgnoreCase(customer.getCity()))
                    .collect(Collectors.toList());

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return customers;
    }

    public List<Customer> getAllCustomers() throws CustomerTableEmptyException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        List<Customer> customers = null;

        try {
            transaction = session.beginTransaction();

            Query<Customer> query = session.createQuery("FROM Customer", Customer.class);
            customers = query.getResultList();

            if (customers.isEmpty()) {
                throw new CustomerTableEmptyException("No data found in customer table");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            if (e instanceof CustomerTableEmptyException) {
                throw e;
            }
            e.printStackTrace();
        }

        return customers;
    }

    public void updateCustomerAddress(BigInteger customerId, String newCity) throws CustomerDoesNotExistException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Customer customer = session.get(Customer.class, customerId);
            if (customer == null) {
                throw new CustomerDoesNotExistException("No customer found for given unique Id");
            }

            customer.setCity(newCity);
            session.update(customer);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            if (e instanceof CustomerDoesNotExistException) {
                throw e;
            }
            e.printStackTrace();
        }
    }
}