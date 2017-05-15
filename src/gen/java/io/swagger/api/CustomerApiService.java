package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Customer;
import io.swagger.model.CustomerData;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-15T21:13:37.898+02:00")
public abstract class CustomerApiService {
    public abstract Response createCustomer(CustomerData body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteCustomer(String customerId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response findCustomer( String name, String address, String phone,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getCustomerById(String customerId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateCustomer(String customerId,CustomerData body,SecurityContext securityContext) throws NotFoundException;
}
