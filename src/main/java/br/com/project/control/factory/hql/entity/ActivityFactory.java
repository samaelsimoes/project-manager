package br.com.project.control.factory.hql.entity;

import br.com.project.control.entity.pojo.Adm;
import br.com.project.control.entity.pojo.Activity;
import br.com.project.control.factory.hql.ObjMapper;
import br.com.project.control.exception.GlobalException;

import java.util.List;
import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import jdk.nashorn.internal.ir.ObjectNode;

public class ActivityFactory extends ObjMapper {
    private List<Activity> activity = new ArrayList<Activity>();

    public ActivityFactory(String activityStr) throws GlobalException {

        try {
            Activity obj = getObject().readValue(activityStr, Activity.class);
            if(obj != null)
                this.activity.add(obj);
            else
                throw new GlobalException("Erro factory in class ActivityFactory");

        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException("Erro factory in class ActivityFactory ");
        }
    }

    public List<Activity> getList() {
        return activity;
    }

    public void setActivity(List<Activity> activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity.get(0);
    }
}
