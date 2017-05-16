package com.tapifolti.videorentalstore.db.inmemorydata;

import com.tapifolti.videorentalstore.db.FilmDao;
import com.tapifolti.videorentalstore.api.FilmCore;
import io.swagger.model.FilmData;
import io.swagger.model.FilmKind;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by tapifolti on 5/15/2017.
 */
public class InMemoryFilms implements FilmDao {

    /***
     * thread safe FilmCore to be stored in thread safe map
     */
    private static class SyncFilm {
        private FilmCore film;

        public SyncFilm(FilmCore film) {
            this.film = film.clone();
        }

        public synchronized FilmCore getClone() {
            return film.clone();
        }

        public synchronized String getTitle() {
            return film.getTitle();
        }

        public synchronized void setTitle(String title) {
            film.setTitle(title);
        }

        public synchronized FilmKind getKind() {
            return film.getKind();
        }

        public synchronized void setKind(FilmKind kind) {
            film.setKind(kind);
        }

        public synchronized void setDeleted(boolean deleted) {
            film.setDeleted(deleted);
        }

        /*** rent this film - it should be atomic
         *
         * @param customerId
         * @return
         */
        public synchronized boolean rentFilm(String customerId, int rentedForDays) {
            if (!film.getDeleted() && film.getRentedBy() == null) {
                film.setRentedBy(customerId);
                film.setRentedOn(LocalDate.now());
                film.setRentedForDays(rentedForDays);
                return true;
            } else {
                return false;
            }

        }

        /*** return this film - it should be atomic
         *
         * @param customerId
         * @return
         */
        public synchronized int returnFilm(String customerId) {
            if (film.getRentedBy() == null || film.getRentedOn() == null) {
                return 0;
            }
            // swagger generated java.util.Date :(
            LocalDate now = LocalDate.now();
            LocalDate then = film.getRentedOn();;
            int totalDays = (int)ChronoUnit.DAYS.between(then, now);
            film.setRentedBy(null);
            film.setRentedOn(null);
            film.setRentedForDays(0);
            return totalDays;
        }

    }

    private Map<String, SyncFilm> films = new ConcurrentHashMap<>();

    @Override
    public FilmCore createFilm(FilmData body) {
        FilmCore film = new FilmCore(UUID.randomUUID().toString(), body.getTitle(), body.getKind(), null, null, false, 0);
        films.put(film.getFilmId(), new SyncFilm(film));
        return film;
    }

    @Override
    public Optional<FilmCore> deleteFilm(String filmId) {
        Optional<SyncFilm> optSyncFilm = Optional.ofNullable(films.get(filmId));
        optSyncFilm.ifPresent(x -> x.setDeleted(true));
        return optSyncFilm.map(x -> x.getClone());
    }

    @Override
    public List<FilmCore> findFilm(String title, FilmKind kind) {
        return films
                .values()
                .stream()
                .filter(x -> (x.getTitle()!=null && title!=null && x.getTitle().matches(title)) ||
                                (x.getKind()!=null && kind!=null && x.getKind().equals(kind)) ||
                                (title==null && kind==null))
                .map(x -> x.getClone())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FilmCore> getFilmById(String filmId) {
        Optional<SyncFilm> optSyncFilm = Optional.ofNullable(films.get(filmId));
        return optSyncFilm.map(x -> x.getClone());
    }

    private void updateFilm(SyncFilm x, FilmData body) {
        if (body.getTitle() != null){
            x.setTitle(body.getTitle());
        }
        if (body.getKind() != null) {
            x.setKind(body.getKind());
        }
    }

    @Override
    public Optional<FilmCore> updateFilm(String filmId, FilmData body) {
        Optional<SyncFilm> optSyncFilm = Optional.ofNullable(films.get(filmId));
        optSyncFilm.ifPresent(x -> updateFilm(x, body));
        return optSyncFilm.map(x -> x.getClone());
    }


    @Override
    public boolean rentFilm(String filmId, String customerId, int rentedForDays) {
        Optional<SyncFilm> optSyncFilm = Optional.ofNullable(films.get(filmId));
        boolean rented = false;
        if (optSyncFilm.isPresent()) {
            rented = optSyncFilm.get().rentFilm(customerId, rentedForDays);
        }
        return rented;
    }

    @Override
    public int returnFilm(String filmId, String customerId) {
        Optional<SyncFilm> optSyncFilm = Optional.ofNullable(films.get(filmId));
        int totalDays = 0;
        if (optSyncFilm.isPresent()) {
            totalDays = optSyncFilm.get().returnFilm(customerId);
        }
        return totalDays ;

    }

}
