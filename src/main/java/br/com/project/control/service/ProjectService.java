package br.com.project.control.service;

import br.com.project.control.daoconnect.ProjectDAO;
import br.com.project.control.datatype.HOperator;
import br.com.project.control.entity.pojo.Project;
import br.com.project.control.exception.GlobalException;
import br.com.project.control.interfaces.ProjectServiceInterface;

import java.util.List;

public class ProjectService implements ProjectServiceInterface<Project> {

    protected Project project;
    protected List<Project> projects;
    protected int num;
    protected String valueStr;

    public ProjectService() {
    }
    public ProjectService(Project project) {
        this.project = project;
    }
    public ProjectService(String valueStr) {
        this.valueStr = valueStr;
    }
    public ProjectService(int num) {
        this.num = num;
    }

    public void add() throws GlobalException {
        new ProjectDAO(this.project).persist();
    }
    public List<Project> pesquisaGeneric (String field, HOperator op, String value) throws GlobalException {
        return (List<Project>) new ProjectDAO().findGeneric(field, op, value);
    }
    public List<Project> searchProjectGeneric(String field, HOperator op, int num) {
        return null;
    }


    @Override
    public List<Project> searchProject() throws GlobalException {
        String type = Integer.toString(this.num);
        return (List<Project>) new ProjectDAO().findGeneric("type", HOperator.EQUALS, "0");
    }
}

