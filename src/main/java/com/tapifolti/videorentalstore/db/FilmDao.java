package com.tapifolti.videorentalstore.db;

import io.swagger.model.Film;
import io.swagger.model.FilmData;
import io.swagger.model.FilmKind;

import java.util.List;
import java.util.Optional;

/**
 * Created by tapifolti on 5/15/2017.
 */
public interface FilmDao {
    public Film createFilm(FilmData body);
    public Optional<Film> deleteFilm(String filmId);
    public List<Film> findFilm(String title, FilmKind kind);
    public Optional<Film> getFilmById(String filmId);
    public Optional<Film> updateFilm(String filmId, FilmData body);
}
