package io.swagger.api.factories;

import io.swagger.api.RentApiService;
import com.tapifolti.videorentalstore.resources.RentApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-15T13:28:25.494+02:00")
public class RentApiServiceFactory {
    private final static RentApiService service = new RentApiServiceImpl();

    public static RentApiService getRentApi() {
        return service;
    }
}
