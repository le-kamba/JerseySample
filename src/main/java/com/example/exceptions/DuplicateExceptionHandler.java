package com.example.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DuplicateExceptionHandler implements ExceptionMapper<DuplicateIdException> {

    public Response toResponse(DuplicateIdException ex) {
        return Response.status(Response.Status.CONFLICT).build();
    }
}

