package com.tapifolti.videorentalstore.resources;

import com.tapifolti.videorentalstore.api.RentConditions;
import com.tapifolti.videorentalstore.api.RentReturnLogic;
import com.tapifolti.videorentalstore.db.CustomerDao;
import com.tapifolti.videorentalstore.db.FilmDao;
import io.swagger.api.*;

import io.swagger.model.Customer;
import io.swagger.model.ReturnDesc;

import io.swagger.api.NotFoundException;
import io.swagger.model.ReturnResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-15T13:31:36.632+02:00")
public class ReturnApiServiceImpl extends ReturnApiService {
    final static Logger log = LoggerFactory.getLogger(ReturnApiServiceImpl.class);

    public ReturnApiServiceImpl(FilmDao filmDao, CustomerDao customerDao, RentConditions rentConditions) {
        this.filmDao = filmDao;
        this.customerDao = customerDao;
        this.rentConditions = rentConditions;
    }
    private CustomerDao customerDao;
    private FilmDao filmDao;
    private RentConditions rentConditions;

    @Override
    public Response callReturn(ReturnDesc body, SecurityContext securityContext) throws NotFoundException {
        log.info("Action 'return'" + body.toString());
        Optional<Customer> optCustomer = customerDao.getCustomerById(body.getCustomerId());
        if (!optCustomer.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else if (optCustomer.get().getSuspended()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Customer is suspended").build();
        }
        ReturnResponse[] returnResponseArr = new RentReturnLogic().returnFunc(rentConditions, filmDao, customerDao, optCustomer.get(), body.getFilmIds());
        return Response.ok().entity(returnResponseArr).build();
    }
}
