package io.swagger.api;

import com.tapifolti.videorentalstore.api.RentConditions;
import com.tapifolti.videorentalstore.db.CustomerDao;
import com.tapifolti.videorentalstore.db.FilmDao;
import com.tapifolti.videorentalstore.resources.RentApiServiceImpl;
import io.swagger.annotations.ApiParam;

import io.swagger.model.RentDesc;
import io.swagger.model.RentResponse;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/rent")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the rent API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-16T16:06:48.217+02:00")
public class RentApi  {
    private RentApiService delegate;

    @Inject
    public RentApi(FilmDao filmDao, CustomerDao customerDao, RentConditions rentConditions) {
       delegate = new RentApiServiceImpl(filmDao, customerDao, rentConditions);
    }

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
