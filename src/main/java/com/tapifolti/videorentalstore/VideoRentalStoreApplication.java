package com.tapifolti.videorentalstore;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        // TODO: implement application
    }

}
