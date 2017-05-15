package com.tapifolti.videorentalstore.resources;

import io.swagger.api.*;

import io.swagger.model.ReturnDesc;

import io.swagger.api.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-05-15T13:31:36.632+02:00")
public class ReturnApiServiceImpl extends ReturnApiService {
    final static Logger log = LoggerFactory.getLogger(ReturnApiServiceImpl.class);

    @Override
    public Response callReturn(ReturnDesc body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        log.info("Action 'return'" + body.toString());
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
