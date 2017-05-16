package com.tapifolti.videorentalstore;

import com.google.common.collect.ImmutableMap;
import com.tapifolti.videorentalstore.api.RentConditions;
import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class VideoRentalStoreConfiguration extends Configuration {
    // TODO: implement service configuration


    @NotNull
    private RentConditions rentConditions = new RentConditions();

    @JsonProperty("rentConditions")
    public RentConditions getRentConditions() {
        return rentConditions;
    }

    @JsonProperty("rentConditions")
    public void setRentConditions(Map<String, Integer> rentConditions) {
        rentConditions.clear();
        for (Map.Entry<String, Integer> entry : rentConditions.entrySet()) {
            rentConditions.put(entry.getKey(), entry.getValue());
        }
    }
}
