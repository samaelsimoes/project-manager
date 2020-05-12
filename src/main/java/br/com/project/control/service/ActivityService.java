package br.com.project.control.service;

import br.com.project.control.daoconnect.ActivityDAO;
import br.com.project.control.datatype.HOperator;
import br.com.project.control.entity.pojo.Activity;
import br.com.project.control.exception.GlobalException;
import br.com.project.control.interfaces.ActivityServiceInterface;

import java.util.List;

public class ActivityService implements ActivityServiceInterface<Activity> {

    protected Activity activity;
    protected List<Activity> activitys;
    protected int num;
    protected String valueStr;

    public ActivityService() {}

    public ActivityService(Activity activity) {
        this.activity = activity;
    }
    public ActivityService(String valueStr) {
        this.valueStr = valueStr;
    }
    public ActivityService(int num) {
        this.num = num;
    }

    public void add() throws GlobalException {
        new ActivityDAO(this.activity).persist();
    }

    @Override
    public List<Activity> searchActivity()throws GlobalException {
        String type = Integer.toString(this.num);
        return (List<Activity>) new ActivityDAO().findGeneric("type", HOperator.EQUALS, "0");
    }
}
