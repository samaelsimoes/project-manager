package br.com.project.control.interfaces;

import br.com.project.control.entity.pojo.Activity;
import br.com.project.control.exception.GlobalException;

import java.util.List;

public interface ActivityServiceInterface<T> {

    List<Activity> searchActivity() throws GlobalException;
}