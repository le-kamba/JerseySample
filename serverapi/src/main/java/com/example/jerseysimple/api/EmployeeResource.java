package com.example.jerseysimple.api;

import com.example.jerseysimple.repository.EmployeeRepository;
import com.example.jerseysimple.repository.models.Employee;

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

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateEmployee(Employee employee) {
        EmployeeRepository.getInstance().update(employee.getId(), employee.getFirstName());
        // 新規作成した場合はcreatedを返す必要があるが、このサンプルではエラーとするため、常にokを返す
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteEmployee(@PathParam("id") int id) {
        EmployeeRepository.getInstance().delete(id);
        // Entityの状態を返す場合はokを返す。
        // 受け付けたが処理が終わっていない場合は(キューに乗っただけなど)acceptedを返す
        // このサンプルでは削除が完了して該当コンテントがなくなったことだけ返す
        return Response.noContent().build();
    }
}
