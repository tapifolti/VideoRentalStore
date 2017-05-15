package com.tapifolti.videorentalstore.resources;

import com.tapifolti.videorentalstore.db.FilmDao;
import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.Error;
import io.swagger.model.Film;
import io.swagger.model.FilmData;

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
public class FilmApiServiceImpl extends FilmApiService {

    @Inject
    FilmDao filmDao;

    @Override
    public Response createFilm(FilmData body, SecurityContext securityContext) throws NotFoundException {
        Film film = filmDao.createFilm(body);
        return Response.ok().entity(film).build();
    }

    @Override
    public Response deleteFilm(String filmId, SecurityContext securityContext) throws NotFoundException {
        Optional<Film> optFilm = filmDao.deleteFilm(filmId);
        if (optFilm.isPresent()) {
            return Response.ok().entity(optFilm.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response findFilm( String title,  String kind, SecurityContext securityContext) throws NotFoundException {
        // TODO: swagger generation error: 'FilmKind kind' expected -- validate, response invalid arg
        List<Film> films = filmDao.findFilm(title, FilmKind.fromValue(kind));
        if (!films.isEmpty()) {
            return Response.ok().entity(films).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response getFilmById(String filmId, SecurityContext securityContext) throws NotFoundException {
        Optional<Film> optFilm = filmDao.getFilmById(filmId);
        if (optFilm.isPresent()) {
            return Response.ok().entity(optFilm.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response updateFilm(String filmId, FilmData body, SecurityContext securityContext) throws NotFoundException {
        Optional<Film> optFilm = filmDao.updateFilm(filmId, body);
        if (optFilm.isPresent()) {
            return Response.ok().entity(optFilm.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
