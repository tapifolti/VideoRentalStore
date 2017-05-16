package com.tapifolti.videorentalstore.db.inmemorydata;

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

    /***
     * thread safe Customer to be stored in thread safe map
     */
    private static class SyncCustomer {

        private Customer customer;

        private Customer copyCustomer(Customer customer) {
            Customer retCustomer = new Customer()
                    .customerId(customer.getCustomerId())
                    .name(customer.getName())
                    .address(customer.getAddress())
                    .phone(customer.getPhone())
                    .bonusPoints(customer.getBonusPoints())
                    .suspended(customer.getSuspended());
            return retCustomer;
        }

        public SyncCustomer(Customer customer) {
            this.customer = copyCustomer(customer);
        }

        public synchronized Customer getClone() {
            return copyCustomer(customer);
        }

        public synchronized String getName() {
            return customer.getName();
        }
        public synchronized void setName(String name) {
            customer.setName(name);
        }

        public synchronized String getAddress() {
            return customer.getAddress();
        }
        public synchronized void setAddress(String address) {
            customer.setAddress(address);
        }

        public synchronized String getPhone() {
            return customer.getPhone();
        }
        public synchronized void setPhone(String phone) {
            customer.setPhone(phone);
        }

        public synchronized void setSuspended(boolean suspended) {
            customer.setSuspended(suspended);
        }

        public synchronized void addBonusPoints(int points) {
            customer.setBonusPoints(customer.getBonusPoints()+points);
        }
    }
    private Map<String, SyncCustomer> customers = new ConcurrentHashMap<>();

    @Override
    public Customer createCustomer(CustomerData data) {
        Customer customer = new Customer()
                .customerId(UUID.randomUUID().toString())
                .name(data.getName())
                .address(data.getAddress())
                .phone(data.getPhone())
                .bonusPoints(0)
                .suspended(false);
        customers.put(customer.getCustomerId(), new SyncCustomer(customer));
        return customer;
    }

    @Override
    public Optional<Customer> deleteCustomer(String customerId) {
        Optional<SyncCustomer> optSyncCustomer = Optional.ofNullable(customers.get(customerId));
        optSyncCustomer.ifPresent(x -> x.setSuspended(true));
        return optSyncCustomer.map(x -> x.getClone());
    }

    @Override
    public List<Customer> findCustomer(String name, String address, String phone) {
        return customers
                .values()
                .stream()
                .filter(x -> (x.getName()!=null && name !=null && x.getName().matches(name)) ||
                        (x.getAddress()!=null && address!=null && x.getAddress().matches(address)) ||
                        (x.getPhone()!=null && phone!=null && x.getPhone().matches(phone)) ||
                        (name==null && address==null && phone==null))
                .map(x -> x.getClone())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> getCustomerById(String customerId) {
        if (customerId == null) {
            return Optional.empty();
        }
        Optional<SyncCustomer> optSyncCustomer = Optional.ofNullable(customers.get(customerId));
        return optSyncCustomer.map(x -> x.getClone());
    }

    private void updateCustomer(SyncCustomer x, CustomerData body) {
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
        Optional<SyncCustomer> optSyncCustomer = Optional.ofNullable(customers.get(customerId));
        optSyncCustomer.ifPresent(x -> updateCustomer(x, body));
        return optSyncCustomer.map(x -> x.getClone());
    }

    @Override
    public Optional<Customer> addBonusPoints(String customerId, int points) {
        Optional<SyncCustomer> optSyncCustomer = Optional.ofNullable(customers.get(customerId));
        optSyncCustomer.ifPresent(x -> x.addBonusPoints(points));
        return optSyncCustomer.map(x -> x.getClone());
    }

}
