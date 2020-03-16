package com.example.jerseysimple.api;

import com.example.jerseysimple.repository.models.Employee;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!\n\n";
    }

    @GET
    @Path("/one")
    @Produces(MediaType.APPLICATION_XML)
    public Employee getOne(){
        Employee employee = new Employee(1, "Unknown");
        return employee;
    }
}
