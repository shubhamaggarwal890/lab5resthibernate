package com.example.erp.controller;
import com.example.erp.*;
import com.example.erp.bean.Students;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

@Path("students")
public class StudentsController {

    @POST
    @Path("/register")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerStudent(Students student) throws URISyntaxException {
        System.out.println(student.getFirst_name());
        System.out.println(student.getLast_name());
        System.out.println(student.getEmail());
        return Response.ok().build();

    }
}
