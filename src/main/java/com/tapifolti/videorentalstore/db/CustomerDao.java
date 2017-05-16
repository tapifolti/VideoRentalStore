package com.tapifolti.videorentalstore.db;

import io.swagger.model.Customer;
import io.swagger.model.CustomerData;

import java.util.List;
import java.util.Optional;

/**
 * Created by tapifolti on 5/15/2017.
 */
public interface CustomerDao {

    public Customer createCustomer(CustomerData data);
    public Optional<Customer> deleteCustomer(String customerId);
    public List<Customer> findCustomer(String name, String address, String phone);
    public Optional<Customer> getCustomerById(String customerId);
    public Optional<Customer> updateCustomer(String customerId, CustomerData body);
    public Optional<Customer> addBonusPoints(String customerId, int points);

}
