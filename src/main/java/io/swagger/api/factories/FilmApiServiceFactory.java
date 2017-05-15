package io.swagger.api.factories;

import io.swagger.api.FilmApiService;
import com.tapifolti.videorentalstore.resources.FilmApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-15T13:28:25.494+02:00")
public class FilmApiServiceFactory {
    private final static FilmApiService service = new FilmApiServiceImpl();

    public static FilmApiService getFilmApi() {
        return service;
    }
}
