package br.com.project.control.interfaces;

import br.com.project.control.entity.pojo.Project;
import br.com.project.control.exception.GlobalException;

import java.util.List;

public interface ProjectServiceInterface<T> {


    List<Project> searchProject() throws GlobalException;
}
