package com.tapifolti.videorentalstore;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.api.CustomerApi;
import io.swagger.api.FilmApi;
import io.swagger.api.RentApi;
import io.swagger.api.ReturnApi;

public class VideoRentalStoreApplication extends Application<VideoRentalStoreConfiguration> {

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
        environment.jersey().register(new CustomerApi());
        environment.jersey().register(new FilmApi());
        environment.jersey().register(new RentApi());
        environment.jersey().register(new ReturnApi());
        environment.jersey().register(new DependencyInjection());
    }

}
