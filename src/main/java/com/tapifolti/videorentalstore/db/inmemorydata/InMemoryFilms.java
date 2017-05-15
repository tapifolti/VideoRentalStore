package com.tapifolti.videorentalstore.db.inmemorydata;

import com.tapifolti.videorentalstore.db.FilmDao;
import io.swagger.model.Film;
import io.swagger.model.FilmData;
import io.swagger.model.FilmKind;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by tapifolti on 5/15/2017.
 */
public class InMemoryFilms implements FilmDao {

    private Map<String, Film> films = new ConcurrentHashMap<>();

    @Override
    public Film createFilm(FilmData body) {
        Film film = new Film();
        film.setFilmId(UUID.randomUUID().toString());
        film.setTitle(body.getTitle());
        film.setKind(body.getKind());
        film.setDeleted(false);
        films.put(film.getFilmId(), film);
        return film;
    }

    @Override
    public Optional<Film> deleteFilm(String filmId) {
        return Optional.ofNullable(films.remove(filmId));
    }

    @Override
    public List<Film> findFilm(String title, FilmKind kind) {
        return films
                .values()
                .stream()
                .filter(x -> (x.getTitle()!=null && title!=null && x.getTitle().matches(title)) ||
                                (x.getKind()!=null && kind!=null && x.getKind().equals(kind)))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Film> getFilmById(String filmId) {
        return Optional.ofNullable(films.get(filmId));
    }

    private void updateFilm(Film x, FilmData body) {
        if (body.getTitle() != null){
            x.setTitle(body.getTitle());
        }
        if (body.getKind() != null) {
            x.setKind(body.getKind());
        }
    }

    @Override
    public Optional<Film> updateFilm(String filmId, FilmData body) {
        Optional<Film> ret = Optional.ofNullable(films.remove(filmId));
        ret.ifPresent(x -> updateFilm(x, body));
        return ret;
    }
}
