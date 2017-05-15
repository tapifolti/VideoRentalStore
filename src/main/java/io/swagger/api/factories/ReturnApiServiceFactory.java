package io.swagger.api.factories;

import io.swagger.api.ReturnApiService;
import com.tapifolti.videorentalstore.resources.ReturnApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-15T13:28:25.494+02:00")
public class ReturnApiServiceFactory {
    private final static ReturnApiService service = new ReturnApiServiceImpl();

    public static ReturnApiService getReturnApi() {
        return service;
    }
}
