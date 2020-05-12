package br.com.project.control.factory.hql.entity;

import br.com.project.control.entity.pojo.Project;
import br.com.project.control.exception.GlobalException;
import br.com.project.control.factory.hql.ObjMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectFactory extends ObjMapper {
    private List<Project> project = new ArrayList<Project>();

    public ProjectFactory(String projectStr) throws GlobalException {

        try {
            Project obj = getObject().readValue(projectStr, Project.class);
            if(obj != null)
                this.project.add(obj);
            else
                throw new GlobalException("Erro factory in class ActivityFactory");

        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException("Erro factory in class ActivityFactory ");
        }
    }

    public List<Project> getList() {
        return project;
    }

    public void setActivity(List<Project> project) {
        this.project = project;
    }

    public Project getActivity() {
        return project.get(0);
    }
}
