package com.example.erp.controller;

import com.example.erp.bean.Courses;
import com.example.erp.bean.Students;
import com.example.erp.service.CourseService;
import com.example.erp.service.StudentService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Path("students")
public class StudentsController {
    StudentService studentService = new StudentService();
    CourseService courseService =  new CourseService();
    @POST
    @Path("/register")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerStudent(Students student) throws URISyntaxException {
        System.out.println();
        List<Courses> courses = new ArrayList<>();
        Courses course = courseService.getCourseByID(student.getCourses().get(0).getCourse_id());
        if(course!=null){
            courses.add(course);
            student.setCourses(courses);
            if(studentService.registerStudent(student)){
                return Response.ok().build(); // Response 200
            }else{
                return Response.status(203).build();  //Response 203
            }

        }
        return Response.status(203).build();

    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginStudent(Students student) throws URISyntaxException {
        Students result = studentService.verifyEmail(student);
        if(result == null){
            return Response.noContent().build();
        }

        return Response.ok().entity(result).build();
    }

    @POST
    @Path("/image")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response imageUpload(@FormDataParam("file") InputStream fileInputStream,
                                @FormDataParam("file") FormDataContentDisposition fileMetaData) throws URISyntaxException {

        // Do this stuff in service
        String upload_path = "/home/shubham/Documents/"+fileMetaData.getFileName();
        try{
            int read = 0;
            byte[] bytes = new byte[1024];

            OutputStream out = new FileOutputStream(new File(upload_path));
            while ((read = fileInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        StreamingOutput fileStream = new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                java.nio.file.Path path = Paths.get(upload_path);
                byte[] data = Files.readAllBytes(path);
                outputStream.write(data);
                outputStream.flush();
            }
        };

        return Response
                .ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition","attachment; filename = "+fileMetaData.getFileName())
                .build();
    }
}
