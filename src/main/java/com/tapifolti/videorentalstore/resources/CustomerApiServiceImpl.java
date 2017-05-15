package com.tapifolti.videorentalstore.resources;

import com.tapifolti.videorentalstore.db.CustomerDao;
import com.tapifolti.videorentalstore.db.FilmDao;
import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.Customer;
import io.swagger.model.CustomerData;
import io.swagger.model.Error;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;
import java.util.Optional;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-15T13:31:36.632+02:00")
public class CustomerApiServiceImpl extends CustomerApiService {

    @Inject
    CustomerDao customerDao;

    @Override
    public Response createCustomer(CustomerData body, SecurityContext securityContext) throws NotFoundException {
        Customer customer = customerDao.createCustomer(body);
        return Response.ok().entity(customer).build();
    }

    @Override
    public Response deleteCustomer(String customerId, SecurityContext securityContext) throws NotFoundException {
        Optional<Customer> optCustomer = customerDao.deleteCustomer(customerId);
        if (optCustomer.isPresent()) {
            return Response.ok().entity(optCustomer.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response findCustomer( String name,  String address,  String phone, SecurityContext securityContext) throws NotFoundException {
        List<Customer> customers = customerDao.findCustomer(name, address, phone);
        if (!customers.isEmpty()) {
            return Response.ok().entity(customers).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response getCustomerById(String customerId, SecurityContext securityContext) throws NotFoundException {
        Optional<Customer> optCustomer = customerDao.getCustomerById(customerId);
        if (optCustomer.isPresent()) {
            return Response.ok().entity(optCustomer.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response updateCustomer(String customerId, CustomerData body, SecurityContext securityContext) throws NotFoundException {
        Optional<Customer> optCustomer = customerDao.updateCustomer(customerId, body);
        if (optCustomer.isPresent()) {
            return Response.ok().entity(optCustomer.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
