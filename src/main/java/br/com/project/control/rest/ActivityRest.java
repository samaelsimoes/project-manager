package br.com.project.control.rest;

import br.com.project.control.entity.pojo.Activity;
import br.com.project.control.entity.pojo.Project;
import br.com.project.control.exception.GlobalException;
import br.com.project.control.factory.hql.ObjMapper;
import br.com.project.control.factory.hql.entity.ActivityFactory;
import br.com.project.control.service.ActivityService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

import br.com.project.control.service.ProjectService;
import com.google.gson.Gson;

@Path("/activity")
public class ActivityRest extends ObjMapper {

    public ActivityRest() { }

    @POST
    @Consumes("application/*")
    public Response add(String activityStr) throws GlobalException, IOException {
        try {
            if (activityStr != null && !activityStr.isEmpty()) {
                Activity activity = new ActivityFactory(activityStr).getActivity();

                if (activity != null) {
                    new ActivityService(activity).add();
                } else {
                    return this.buildResponse("no value was sent to the server");
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
            List<Activity> activity = new ActivityService(str).searchActivity();
            resp = getJson(activity);

            return Response.ok( resp ,MediaType.APPLICATION_JSON).build();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new GlobalException("Erro ao fazer a consulta por nome");
        }
    }
}
