package com.tapifolti.videorentalstore.api;

import com.tapifolti.videorentalstore.db.CustomerDao;
import com.tapifolti.videorentalstore.db.FilmDao;
import io.swagger.model.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by tapifolti on 5/15/2017.
 */
public class RentReturnLogic {


    private int calcPrice(RentConditions rentConditions, FilmKind kind, int days) {
        int initDays = 0;
        int initPrice = 0;
        int dayPrice = 0;
        switch (kind){
            case NEW:
                initDays = rentConditions.get("premiumInitialDays");
                initPrice = rentConditions.get("premiumInitialPrice");
                dayPrice = rentConditions.get("premiumDayPrice");
                break;
            case REGULAR:
                initDays = rentConditions.get("regularInitialDays");
                initPrice = rentConditions.get("regularInitialPrice");
                dayPrice = rentConditions.get("regularDayPrice");
                break;
            case OLD:
                initDays = rentConditions.get("oldInitialDays");
                initPrice = rentConditions.get("oldInitialPrice");
                dayPrice = rentConditions.get("oldDayPrice");
                break;
        }
        if (days <= initDays) {
            return initPrice;
        } else {
            return initPrice + (days-initDays) * dayPrice;
        }
    }

    private int calcBonus(RentConditions rentConditions, FilmKind kind) {
        int bonus = 0;
        switch (kind){
            case NEW:
                bonus = rentConditions.get("premiumBonusPoint");
                break;
            case REGULAR:
                bonus = rentConditions.get("regularBonusPoint");
                break;
            case OLD:
                bonus = rentConditions.get("oldBonusPoint");
                break;
        }
        return bonus;
    }

    public RentResponse[] rent(RentConditions rentConditions, FilmDao filmDao, CustomerDao customerDao, Customer renter, List<FilmRent> filmsToRent) {
        RentResponse[] rentResponse = new RentResponse[filmsToRent.size()];
        int item = 0;
        for (FilmRent toRent : filmsToRent) {
            RentResponse response = new RentResponse();
            rentResponse[item++] = response;
            response.filmId(toRent.getFilmId());
            Optional<FilmCore> optFilm = filmDao.getFilmById(toRent.getFilmId());
            if (!optFilm.isPresent()) {
                response.setMessage("No such film");
            } else if (optFilm.get().getDeleted()) {
                response.setMessage("Film is deleted");
            } else if (optFilm.get().getRentedBy() != null) {
                response.setMessage("Film is rented by: '" + optFilm.get().getResolved(customerDao).getRentedBy() + "' on '" + optFilm.get().getRentedOn() + "'");
            } else { // rent it
                boolean rented = filmDao.rentFilm(toRent.getFilmId(), renter.getCustomerId(), toRent.getDays());
                if (rented) {
                    int price = calcPrice(rentConditions, optFilm.get().getKind(), toRent.getDays());
                    int bonus = calcBonus(rentConditions, optFilm.get().getKind());
                    response.setPay(price);
                    response.setBonusPoints(bonus);
                    customerDao.addBonusPoints(renter.getCustomerId(), bonus);
                } else {
                    optFilm = filmDao.getFilmById(toRent.getFilmId());
                    response.setMessage("Cannot be rented, check film's attribute: " + (optFilm.isPresent()? optFilm.get().getResolved(customerDao): "film not found"));
                }
            }
        }
        return rentResponse;
    }

    public ReturnResponse[] returnFunc(RentConditions rentConditions, FilmDao filmDao, CustomerDao customerDao, Customer renter, List<String> filmsToRent) {
        ReturnResponse[] returnResponse = new ReturnResponse[filmsToRent.size()];
        int item = 0;
        for (String filmId : filmsToRent) {
            ReturnResponse response = new ReturnResponse();
            returnResponse[item++] = response;
            response.filmId(filmId);
            Optional<FilmCore> optFilm = filmDao.getFilmById(filmId);
            if (!optFilm.isPresent()) {
                response.setMessage("No such film");
            } else if (optFilm.get().getRentedBy() == null) {
                response.setMessage("Film is not rented");
            } else { // will be returned
                if (!optFilm.get().getRentedBy().equals(renter.getCustomerId())) {
                    response.setMessage("Film is rented by somebody else: '" + optFilm.get().getResolved(customerDao).getRentedBy() + "' on '" + optFilm.get().getRentedOn() + "'");
                }
                if (optFilm.get().getDeleted()) {
                    response.setMessage("Film is deleted");
                }
                int paidDays = optFilm.get().getRentedForDays();
                int totalDays = filmDao.returnFilm(filmId, renter.getCustomerId());
                int paidPrice = calcPrice(rentConditions, optFilm.get().getKind(), paidDays);
                int totalPrice = calcPrice(rentConditions, optFilm.get().getKind(), totalDays);
                response.setPaid(paidPrice);
                response.setTotalToPay(totalPrice);
                response.setMessage("Please pay additionally: " + Integer.toString(totalPrice-paidPrice));
            }
        }
        return returnResponse;
    }
}
