package com.tapifolti.videorentalstore.db;

import com.tapifolti.videorentalstore.api.FilmCore;
import io.swagger.model.FilmData;
import io.swagger.model.FilmKind;

import java.util.List;
import java.util.Optional;

/**
 * Created by tapifolti on 5/15/2017.
 */
public interface FilmDao {
    public FilmCore createFilm(FilmData body);
    public Optional<FilmCore> deleteFilm(String filmId);
    public List<FilmCore> findFilm(String title, FilmKind kind);
    public Optional<FilmCore> getFilmById(String filmId);
    public Optional<FilmCore> updateFilm(String filmId, FilmData body);
    public boolean rentFilm(String filmId, String customerId, int rentedForDays);
    public int returnFilm(String filmId, String customerId);

}
