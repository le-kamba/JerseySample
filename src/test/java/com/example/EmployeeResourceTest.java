package com.example;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(EmployeeResource.class);
    }

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @AfterEach
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void getAll() {
        final Response response = target("/employees/all").request().get();

        List<Employee> content = response.readEntity(new GenericType<>() {
        });
        assertThat(content.size()).isEqualTo(5);
        assertThat(content.get(0)).isEqualToComparingFieldByField(new Employee(3, "Cupcake"));
        assertThat(content.get(1)).isEqualToComparingFieldByField(new Employee(4, "Donuts"));
        assertThat(content.get(2)).isEqualToComparingFieldByField(new Employee(5, "Eclair"));
        assertThat(content.get(3)).isEqualToComparingFieldByField(new Employee(8, "Froyo"));
        assertThat(content.get(4)).isEqualToComparingFieldByField(new Employee(9, "Gingerbread"));
    }

    @Test
    public void getEmployee() {
        Employee employee = target("/employees/3").request().get(Employee.class);
        assertThat(employee).isEqualToComparingFieldByField(new Employee(3, "Cupcake"));

        employee = target("/employees/4").request().get(Employee.class);
        assertThat(employee).isEqualToComparingFieldByField(new Employee(4, "Donuts"));

        employee = target("/employees/5").request().get(Employee.class);
        assertThat(employee).isEqualToComparingFieldByField(new Employee(5, "Eclair"));

        employee = target("/employees/8").request().get(Employee.class);
        assertThat(employee).isEqualToComparingFieldByField(new Employee(8, "Froyo"));

        employee = target("/employees/9").request().get(Employee.class);
        assertThat(employee).isEqualToComparingFieldByField(new Employee(9, "Gingerbread"));
    }

    @Test
    public void searchEmployee() {
        final List<Employee> content = target("/employees/search")
                .queryParam("name", "a")
                .request()
                .get(new GenericType<>() {
                });

        assertThat(content.size()).isEqualTo(3);
        assertThat(content.get(0)).isEqualToComparingFieldByField(new Employee(3, "Cupcake"));
        assertThat(content.get(1)).isEqualToComparingFieldByField(new Employee(5, "Eclair"));
        assertThat(content.get(2)).isEqualToComparingFieldByField(new Employee(9, "Gingerbread"));
    }

    @Test
    public void addEmployeeJson() {
        String json = "{\"firstName\":\"Honeycomb\",\"id\":11}";
        final Response response = target("/employees").request()
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));
        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getHeaderString("Location"))
                .isEqualTo("http://localhost:9998/employees/11");
    }

    @Test
    public void addEmployeeXml() {
        String json = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<employee><firstName>KitKat</firstName><id>19</id></employee>";
        final Response response = target("/employees").request()
                .post(Entity.entity(json, MediaType.APPLICATION_XML));
        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getHeaderString("Location"))
                .isEqualTo("http://localhost:9998/employees/19");
    }
}