package com.tapifolti.videorentalstore.api;

import com.tapifolti.videorentalstore.db.CustomerDao;
import com.tapifolti.videorentalstore.db.FilmDao;
import io.swagger.model.Customer;
import io.swagger.model.FilmKind;
import io.swagger.model.FilmRent;
import io.swagger.model.RentResponse;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import javax.validation.constraints.AssertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by tapifolti on 5/17/2017.
 */
public class RentReturnLogicTest {

    private static RentConditions rentConditions = new RentConditions();

    @BeforeClass
    public static void loadConditions() {
        rentConditions.put("premiumInitialPrice", 40);
        rentConditions.put("premiumDayPrice", 40);
        rentConditions.put("premiumInitialDays", 1);
        rentConditions.put("premiumBonusPoint", 2);
        rentConditions.put("regularInitialPrice", 30);
        rentConditions.put("regularDayPrice", 30);
        rentConditions.put("regularInitialDays", 3);
        rentConditions.put("regularBonusPoint", 1);
        rentConditions.put("oldInitialPrice", 30);
        rentConditions.put("oldDayPrice", 30);
        rentConditions.put("oldInitialDays", 5);
        rentConditions.put("oldBonusPoint", 1);
    }

    @org.junit.Test
    public void calcPrice() throws Exception {
        RentReturnLogic rentReturnLogic = new RentReturnLogic();
        assertTrue(rentReturnLogic.calcPrice(rentConditions, FilmKind.NEW, 1) == 40); // Matrix 11
        assertTrue(rentReturnLogic.calcPrice(rentConditions, FilmKind.REGULAR, 5) == 90); // Spider Man
        assertTrue(rentReturnLogic.calcPrice(rentConditions, FilmKind.REGULAR, 2) == 30); // Spider Man 2
        assertTrue(rentReturnLogic.calcPrice(rentConditions, FilmKind.OLD, 7) == 90); // Out of Africa
    }

    @org.junit.Test
    public void calcBonus() throws Exception {
        RentReturnLogic rentReturnLogic = new RentReturnLogic();
        assertTrue(rentReturnLogic.calcBonus(rentConditions, FilmKind.NEW) == 2); // Matrix 11
        assertTrue(rentReturnLogic.calcBonus(rentConditions, FilmKind.REGULAR) == 1); // Spider Man
        assertTrue(rentReturnLogic.calcBonus(rentConditions, FilmKind.REGULAR) == 1); // Spider Man 2
        assertTrue(rentReturnLogic.calcBonus(rentConditions, FilmKind.OLD) == 1); // Out of Africa
    }

    @Test
    public void rent() throws Exception {
        String filmId = "filmid1";
        String customerId = "customerid1";
        Optional<FilmCore> optFilm = Optional.of(new FilmCore(filmId, "Matrix 11", FilmKind.NEW, null, null, false, 0 ));
        Customer customer = new Customer()
                .customerId(customerId)
                .name("CustomerA")
                .address("Address 1")
                .phone("21321312321")
                .bonusPoints(0)
                .suspended(false);
        Optional<Customer> optCustomer = Optional.of(customer);
        FilmDao filmDao = mock(FilmDao.class);
        CustomerDao customerDao = mock(CustomerDao.class);
        when(filmDao.getFilmById(filmId)).thenReturn(optFilm);
        when(filmDao.rentFilm(filmId, customerId, 1)).thenReturn(true);
        when(customerDao.addBonusPoints(customerId, 1)).thenReturn(Optional.of(new Customer()));
        when(customerDao.getCustomerById(customerId)).thenReturn(optCustomer);
        List< FilmRent > filmsToRent = new ArrayList<>();
        FilmRent filmRent = new FilmRent();
        filmRent.setFilmId(filmId);
        filmRent.setDays(1);
        filmsToRent.add(filmRent);
        RentResponse[] response = new RentReturnLogic().rent(rentConditions, filmDao, customerDao, customer, filmsToRent);
        assertTrue(response != null && response.length == 1);
        assertTrue(response[0].getFilmId().equals(filmId));
        assertTrue(response[0].getBonusPoints() == 2);
        assertTrue(response[0].getPay() == 40);
        assertTrue(response[0].getMessage() == null);
    }

    @Test
    public void returnFunc() throws Exception {
        // TODO
    }

}