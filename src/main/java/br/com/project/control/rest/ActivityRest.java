package br.com.project.control.rest;

import br.com.project.control.entity.pojo.Activity;
import br.com.project.control.exception.GlobalException;
import br.com.project.control.factory.hql.ObjMapper;
import br.com.project.control.factory.hql.entity.ActivityFactory;
import br.com.project.control.service.ActivityService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.IOException;
import com.google.gson.Gson;

@Path("/activity")
public class ActivityRest extends ObjMapper {

    public ActivityRest() { }

    @POST
    @Consumes("application/*")
    public Response add(String activityStr) throws GlobalException, IOException {
        try {
            if (activityStr != null) {
                Activity activity = new ActivityFactory(activityStr).getActivity();

                if (activity != null) {
                    new ActivityService(activity).add();
                } else {
                    throw new GlobalException("no value was sent to the server");
                }
            } else {
                throw new GlobalException("no value was sent to the server");
            }

            return this.buildResponse("registered successfully.");
        } catch (Throwable e) {
            e.printStackTrace();
            return this.buildErrorResponse(e.getMessage());
        }
    }
}
