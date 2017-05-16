package com.tapifolti.videorentalstore.api;

import com.tapifolti.videorentalstore.db.CustomerDao;
import io.swagger.model.Customer;
import io.swagger.model.Film;
import io.swagger.model.FilmKind;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

/**
 * Created by tapifolti on 5/16/2017.
 */

/***
 * wraps film, stores customerId instead of reference
 */
public class FilmCore {

    private Film film;
    private String rentedByCustomerId;
    private LocalDate rentedOn;
    private int rentedForDays;

    public FilmCore(String filmId, String title, FilmKind kind, String rentedByCustomerId, LocalDate rentedOn, boolean deleted, int rentedforDays) {
        film = new Film()
            .filmId(filmId)
            .title(title)
            .kind(kind)
            .rentedBy(null)
            .rentedOn(null)
            .deleted(deleted);
        this.rentedByCustomerId = rentedByCustomerId;
        this.rentedForDays = rentedforDays;
        this.rentedOn = rentedOn;
    }

    public FilmCore clone() {
        return new FilmCore(film.getFilmId(), film.getTitle(), film.getKind(), rentedByCustomerId, rentedOn, film.getDeleted(), rentedForDays);
    }

    public Film getResolved(CustomerDao customerDao) {
        Optional<Customer> optCustomer = customerDao.getCustomerById(rentedByCustomerId);
        if (optCustomer.isPresent()) {
            film.setRentedBy(optCustomer.get());
            film.setRentedOn(rentedOn.format(DateTimeFormatter.ofPattern("uuuu-MM-dd")));
        } else {
            film.setRentedBy(null);
            film.setRentedOn(null);
        }
        return film;
    }

    public String getFilmId() {
        return film.getFilmId();
    }

    public String getTitle() {
        return film.getTitle();
    }
    public void setTitle(String title) {
        film.setTitle(title);
    }

    public FilmKind getKind() {
        return film.getKind();
    }
    public void setKind(FilmKind kind) {
        film.setKind(kind);
    }

    public boolean getDeleted() {
        return film.getDeleted();
    }
    public void setDeleted(boolean deleted) {
        film.setDeleted(deleted);
    }

    public String getRentedBy() {
        return rentedByCustomerId;
    }
    public void setRentedBy(String customerId) {
        rentedByCustomerId = customerId;
    }

    public LocalDate getRentedOn() {
        return rentedOn;
    }
    public void setRentedOn(LocalDate date) {
        rentedOn = date;
    }

    public int getRentedForDays() {
        return rentedForDays;
    }
    public void setRentedForDays(int days) {
        rentedForDays = days;
    }
}
