package io.swagger.api;

import com.tapifolti.videorentalstore.db.CustomerDao;
import com.tapifolti.videorentalstore.db.FilmDao;
import com.tapifolti.videorentalstore.resources.FilmApiServiceImpl;

import io.swagger.annotations.ApiParam;

import io.swagger.model.Film;
import io.swagger.model.FilmData;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/film")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the film API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-16T16:06:48.217+02:00")
public class FilmApi  {

    @Inject
    public FilmApi(FilmDao filmDao, CustomerDao customerDao) {
     delegate = new FilmApiServiceImpl(filmDao, customerDao);
    }
    private FilmApiService delegate;

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Creates a Film, assigns new filmId", response = Film.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Retruns the created Film", response = Film.class) })
    public Response createFilm(@ApiParam(value = "Film object to be created" ,required=true) FilmData body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createFilm(body,securityContext);
    }
    @DELETE
    @Path("/{filmId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Deletes a Film", response = Film.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Retruns the deleted Film", response = Film.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Film not found", response = Film.class) })
    public Response deleteFilm(@ApiParam(value = "Id of the Film to be deleted",required=true) @PathParam("filmId") String filmId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteFilm(filmId,securityContext);
    }
    @GET
    @Path("/findby")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Finds Film(s)", response = Film.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Retruns the found Film(s)", response = Film.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Film not found", response = Film.class, responseContainer = "List") })
    public Response findFilm(@ApiParam(value = "Regexp matched to title") @QueryParam("title") String title
,@ApiParam(value = "Choose kind", allowableValues="new, regular, old") @QueryParam("kind") String kind
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findFilm(title,kind,securityContext);
    }
    @GET
    @Path("/{filmId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Retrieves film by Id", response = Film.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Retruns the Film", response = Film.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Film not found", response = Film.class) })
    public Response getFilmById(@ApiParam(value = "Id of the Film to be retrieved",required=true) @PathParam("filmId") String filmId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getFilmById(filmId,securityContext);
    }
    @PUT
    @Path("/{filmId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Updates a Film", response = Film.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Retruns the updated Film", response = Film.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Film not found", response = Film.class) })
    public Response updateFilm(@ApiParam(value = "Id of the Film to be updated",required=true) @PathParam("filmId") String filmId
,@ApiParam(value = "Film object's title, kind" ,required=true) FilmData body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateFilm(filmId,body,securityContext);
    }
}
