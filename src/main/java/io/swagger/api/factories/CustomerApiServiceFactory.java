package io.swagger.api.factories;

import io.swagger.api.CustomerApiService;
import com.tapifolti.videorentalstore.resources.CustomerApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-15T13:28:25.494+02:00")
public class CustomerApiServiceFactory {
    private final static CustomerApiService service = new CustomerApiServiceImpl();

    public static CustomerApiService getCustomerApi() {
        return service;
    }
}
