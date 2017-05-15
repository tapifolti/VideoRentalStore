package com.tapifolti.videorentalstore.resources;

import com.tapifolti.videorentalstore.api.RentReturnLogic;
import com.tapifolti.videorentalstore.db.CustomerDao;
import com.tapifolti.videorentalstore.db.FilmDao;
import io.swagger.api.*;

import io.swagger.model.*;

import io.swagger.api.NotFoundException;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-15T13:31:36.632+02:00")
public class RentApiServiceImpl extends RentApiService {
    final static Logger log = LoggerFactory.getLogger(RentApiServiceImpl.class);

    @Inject
    CustomerDao customerDao;

    @Inject
    FilmDao filmDao;

    @Override
    public Response rent(RentDesc body, SecurityContext securityContext) throws NotFoundException {
        log.info("Action 'rent'" + body.toString());
        Optional<Customer> optCustomer = customerDao.getCustomerById(body.getCustomerId());
        if (!optCustomer.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else if (optCustomer.get().getSuspended()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Customer is suspended").build();

        }
        List<RentResponse> rentResponseList = RentReturnLogic.rent(filmDao, optCustomer.get().getCustomerId(), body.getFilms());
        return Response.ok().entity(rentResponseList).build();
    }
}
