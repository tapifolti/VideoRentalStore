package io.swagger.api;

import com.tapifolti.videorentalstore.api.RentConditions;
import com.tapifolti.videorentalstore.db.CustomerDao;
import com.tapifolti.videorentalstore.db.FilmDao;
import com.tapifolti.videorentalstore.resources.RentApiServiceImpl;
import com.tapifolti.videorentalstore.resources.ReturnApiServiceImpl;
import io.swagger.annotations.ApiParam;

import io.swagger.model.ReturnDesc;
import io.swagger.model.ReturnResponse;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/return")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the return API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-16T16:06:48.217+02:00")
public class ReturnApi  {
    private ReturnApiService delegate;

    @Inject
    public ReturnApi(FilmDao filmDao, CustomerDao customerDao, RentConditions rentConditions) {
        delegate = new ReturnApiServiceImpl(filmDao, customerDao, rentConditions);
    }


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
