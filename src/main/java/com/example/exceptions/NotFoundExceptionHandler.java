package com.example.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<EmployeeNotFoundException> {

    public Response toResponse(EmployeeNotFoundException ex) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

