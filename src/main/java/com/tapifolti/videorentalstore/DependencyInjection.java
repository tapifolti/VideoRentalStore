package com.tapifolti.videorentalstore;

import com.tapifolti.videorentalstore.db.CustomerDao;
import com.tapifolti.videorentalstore.db.FilmDao;
import com.tapifolti.videorentalstore.db.inmemorydata.InMemoryCustomers;
import com.tapifolti.videorentalstore.db.inmemorydata.InMemoryFilms;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Created by tapifolti on 5/15/2017.
 */
public class DependencyInjection extends AbstractBinder {
    @Override
    protected void configure() {
        // TODO make it configurable which DB implementation is used
        bind(InMemoryCustomers.class).to(CustomerDao.class);
        bind(InMemoryFilms.class).to(FilmDao.class);
    }

}