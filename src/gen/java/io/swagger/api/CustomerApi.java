package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.CustomerApiService;
import io.swagger.api.factories.CustomerApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Customer;
import io.swagger.model.CustomerData;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/customer")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the customer API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-15T21:13:37.898+02:00")
public class CustomerApi  {
   private final CustomerApiService delegate = CustomerApiServiceFactory.getCustomerApi();

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Creates a Customer, assigns new customerId", response = Customer.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Retruns the created Customer", response = Customer.class) })
    public Response createCustomer(@ApiParam(value = "Customer object to be created" ,required=true) CustomerData body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createCustomer(body,securityContext);
    }
    @DELETE
    @Path("/{customerId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Deletes a Customer", response = Customer.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Retruns the deleted Customer", response = Customer.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Customer not found", response = Customer.class) })
    public Response deleteCustomer(@ApiParam(value = "Id of the Customer to be deleted",required=true) @PathParam("customerId") String customerId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteCustomer(customerId,securityContext);
    }
    @GET
    @Path("/findby")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Finds Customer", response = Customer.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Retruns the found Customer(s)", response = Customer.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Customer not found", response = Customer.class, responseContainer = "List") })
    public Response findCustomer(@ApiParam(value = "Regexp matched to name") @QueryParam("name") String name
,@ApiParam(value = "Regexp matched to address") @QueryParam("address") String address
,@ApiParam(value = "Regexp matched to phone") @QueryParam("phone") String phone
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findCustomer(name,address,phone,securityContext);
    }
    @GET
    @Path("/{customerId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Retrieves Customer by Id", response = Customer.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Retruns Customer", response = Customer.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Customer not found", response = Customer.class) })
    public Response getCustomerById(@ApiParam(value = "Id of the Customer to be retrieved",required=true) @PathParam("customerId") String customerId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getCustomerById(customerId,securityContext);
    }
    @PUT
    @Path("/{customerId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Updates fields of an existing Customer", response = Customer.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Retruns the updated Customer", response = Customer.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Customer not found", response = Customer.class) })
    public Response updateCustomer(@ApiParam(value = "Id of the Customer to be updated",required=true) @PathParam("customerId") String customerId
,@ApiParam(value = "Updates Customer object's name, address, phone" ,required=true) CustomerData body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateCustomer(customerId,body,securityContext);
    }
}
