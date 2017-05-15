package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.RentApiService;
import io.swagger.api.factories.RentApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.RentDesc;
import io.swagger.model.RentResponse;

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

@Path("/rent")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the rent API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-15T21:13:37.898+02:00")
public class RentApi  {
   private final RentApiService delegate = RentApiServiceFactory.getRentApi();

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Customer rents a set of films", response = RentResponse.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Returns the price per Film and the bonus points earned", response = RentResponse.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Customer not found", response = RentResponse.class, responseContainer = "List") })
    public Response rent(@ApiParam(value = "Films rented by customer" ,required=true) RentDesc body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.rent(body,securityContext);
    }
}
