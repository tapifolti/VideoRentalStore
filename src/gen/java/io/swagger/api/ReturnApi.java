package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.ReturnApiService;
import io.swagger.api.factories.ReturnApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.ReturnDesc;
import io.swagger.model.ReturnResponse;

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

@Path("/return")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the return API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-15T21:13:37.898+02:00")
public class ReturnApi  {
   private final ReturnApiService delegate = ReturnApiServiceFactory.getReturnApi();

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Customer returns a set of films", response = ReturnResponse.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Returns the price (paid, due) per Film and the bonus points earned", response = ReturnResponse.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Customer not found", response = ReturnResponse.class, responseContainer = "List") })
    public Response callReturn(@ApiParam(value = "Films returned by Customer" ,required=true) ReturnDesc body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.callReturn(body,securityContext);
    }
}
