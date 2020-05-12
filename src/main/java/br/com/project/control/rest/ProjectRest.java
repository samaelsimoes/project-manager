package br.com.project.control.rest;

import br.com.project.control.entity.pojo.Activity;
import br.com.project.control.entity.pojo.Project;
import br.com.project.control.exception.GlobalException;
import br.com.project.control.factory.hql.ObjMapper;
import br.com.project.control.factory.hql.entity.ProjectFactory;
import br.com.project.control.service.ActivityService;
import br.com.project.control.service.ProjectService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.core.MediaType;


@Path("/project")
public class ProjectRest extends ObjMapper {

    public ProjectRest() { }

    @POST
    @Consumes("application/*")
    public Response add(String projectStr) throws GlobalException, IOException {
        try {
            if (projectStr != null && !projectStr.isEmpty()) {
                Project project = new ProjectFactory(projectStr).getActivity();

                if (project != null) {
                    new ProjectService(project).add();
                } else {
                    throw new GlobalException("no value was sent to the server");
                }
            } else {
                return this.buildResponse("no value was sent to the server");
            }

            return this.buildResponse("registered successfully.");
        } catch (Throwable e) {
            e.printStackTrace();
            return this.buildErrorResponse(e.getMessage());
        }
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getAll(String str) throws GlobalException {

        try {
            String resp = null;
            List<Project> project = new ProjectService(str).searchProject();
            resp = getJson(project);

            return Response.ok( project ,MediaType.APPLICATION_JSON).build();
        }  catch (Throwable e) {
            e.printStackTrace();
            throw new GlobalException("Erro ao fazer a consulta por nome");
        }
    }
}
