package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Film;
import io.swagger.model.FilmData;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-15T21:13:37.898+02:00")
public abstract class FilmApiService {
    public abstract Response createFilm(FilmData body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteFilm(String filmId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response findFilm( String title, String kind,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getFilmById(String filmId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response updateFilm(String filmId,FilmData body,SecurityContext securityContext) throws NotFoundException;
}
