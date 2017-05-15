package com.tapifolti.videorentalstore.db.inmemorydata;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.tapifolti.videorentalstore.db.CustomerDao;
import io.swagger.model.Customer;
import io.swagger.model.CustomerData;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by tapifolti on 5/15/2017.
 */
public class InMemoryCustomers implements CustomerDao {

    private Map<String, Customer> customers = new ConcurrentHashMap<>();

    @Override
    public Customer createCustomer(CustomerData data) {
        Customer customer = new Customer();
        customer.setCustomerId(UUID.randomUUID().toString());
        customer.setName(data.getName());
        customer.setAddress(data.getAddress());
        customer.setPhone(data.getPhone());
        customer.setBonusPoints(0);
        customer.setSuspended(false);
        customers.put(customer.getCustomerId(), customer);
        return customer;
    }

    @Override
    public Optional<Customer> deleteCustomer(String customerId) {
        return Optional.ofNullable(customers.remove(customerId));
    }

    @Override
    public List<Customer> findCustomer(String name, String address, String phone) {
        return customers
                .values()
                .stream()
                .filter(x -> (x.getName()!=null && name !=null && x.getName().matches(name)) ||
                        (x.getAddress()!=null && address!=null && x.getAddress().matches(address)) ||
                        (x.getPhone()!=null && phone!=null && x.getPhone().matches(phone)))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> getCustomerById(String customerId) {
        return Optional.ofNullable(customers.get(customerId));
    }

    private void updateCustomer(Customer x, CustomerData body) {
        if (body.getName() != null){
            x.setName(body.getName());
        }
        if (body.getAddress() != null) {
            x.setAddress(body.getAddress());
        }
        if (body.getPhone() != null) {
            x.setPhone(body.getPhone());
        }
    }

    @Override
    public Optional<Customer> updateCustomer(String customerId, CustomerData body) {
        Optional<Customer> ret = Optional.ofNullable(customers.remove(customerId));
        ret.ifPresent(x -> updateCustomer(x, body));
        return ret;
    }
}
