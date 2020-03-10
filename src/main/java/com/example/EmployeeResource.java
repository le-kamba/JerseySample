package com.example;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/employees")
public class EmployeeResource {

    @Context
    UriInfo uriInfo;

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Employee> getAll() {
        return EmployeeRepository.getInstance().selectAll();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Employee getEmployee(@PathParam("id") int id) {
        return EmployeeRepository.getInstance().select(id);
    }

    @GET
    @Path("/search")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Employee> searchEmployee(@QueryParam("name") String name) {
        return EmployeeRepository.getInstance().search(name);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addEmployee(Employee employee) {
        EmployeeRepository.getInstance().insert(employee.getId(), employee.getFirstName());

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(String.valueOf(employee.getId()));
        return Response.created(builder.build()).build();
    }
}
