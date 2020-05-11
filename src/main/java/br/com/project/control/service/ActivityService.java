package br.com.project.control.service;

import br.com.project.control.daoconnect.ActivityDAO;
import br.com.project.control.entity.pojo.Activity;
import br.com.project.control.exception.GlobalException;
import br.com.project.control.interfaces.Service;

import java.util.List;

public class ActivityService implements Service<Activity> {

    protected Activity activity;
    protected List<Activity> activitys;
    protected int num;
    protected String valueStr;

    public ActivityService() {
    }
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
}
