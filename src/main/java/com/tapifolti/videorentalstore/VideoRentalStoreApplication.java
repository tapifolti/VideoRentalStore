package com.tapifolti.videorentalstore;

import com.tapifolti.videorentalstore.api.RentConditions;
import com.tapifolti.videorentalstore.db.CustomerDao;
import com.tapifolti.videorentalstore.db.FilmDao;
import com.tapifolti.videorentalstore.db.inmemorydata.InMemoryCustomers;
import com.tapifolti.videorentalstore.db.inmemorydata.InMemoryFilms;
import com.tapifolti.videorentalstore.resources.CustomerApiServiceImpl;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.api.CustomerApi;
import io.swagger.api.FilmApi;
import io.swagger.api.RentApi;
import io.swagger.api.ReturnApi;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import java.util.HashMap;

public class VideoRentalStoreApplication extends Application<VideoRentalStoreConfiguration> {

    public static class DependencyInjection extends AbstractBinder {
        private VideoRentalStoreConfiguration configuration;
        public DependencyInjection(VideoRentalStoreConfiguration configuration) {
            this.configuration = configuration;
        }
        @Override
        protected void configure() {
            // TODO make it configurable which DB implementation is used
            bind(new InMemoryCustomers()).to(CustomerDao.class);
            bind(new InMemoryFilms()).to(FilmDao.class);
            RentConditions rentConditions = configuration.getRentConditions();
            bind(rentConditions).to(RentConditions.class);
        }
    }

    public static void main(final String[] args) throws Exception {
        new VideoRentalStoreApplication().run(args);
    }

    @Override
    public String getName() {
        return "VideoRentalStore";
    }

    @Override
    public void initialize(final Bootstrap<VideoRentalStoreConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final VideoRentalStoreConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new DependencyInjection(configuration));
        environment.jersey().register(CustomerApi.class);
        environment.jersey().register(FilmApi.class);
        environment.jersey().register(RentApi.class);
        environment.jersey().register(ReturnApi.class);
    }

}
