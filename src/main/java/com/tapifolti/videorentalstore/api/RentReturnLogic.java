package com.tapifolti.videorentalstore.api;

import com.tapifolti.videorentalstore.db.FilmDao;
import io.swagger.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by tapifolti on 5/15/2017.
 */
public class RentReturnLogic {

    public static List<RentResponse> rent(FilmDao filmDao, String customerId, List<FilmRent> filmsToRent) {
        List<RentResponse> rentResponse = new ArrayList<>();
        // TODO
        for (FilmRent toRent : filmsToRent) {
            RentResponse response = new RentResponse();
            Optional<Film> optFilm = filmDao.getFilmById(toRent.getFilmId());
            if (!optFilm.isPresent()) {
                response.setMessage("No such film: " + toRent.getFilmId());
            } else if (optFilm.get().getDeleted()) {
                response.setMessage("Film is deleted: " + toRent.getFilmId());
            } else if (optFilm.get().getRentedBy() != null) {
                response.setMessage("Film is rented by: " + optFilm.get().getRentedBy());
            }
        }
        return rentResponse;
    }

    public static List<ReturnResponse> returnFunc(ReturnDesc body) {
        // TODO
        return null;
    }
}
