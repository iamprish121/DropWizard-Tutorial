package com.company.dropwizard.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/hello")
public class helloWorldResource {

    @GET
    public String get(){
        return "Hello World";
    }

}
