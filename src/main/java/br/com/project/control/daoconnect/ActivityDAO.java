package br.com.project.control.daoconnect;

import br.com.project.control.dao.OperationsDAO;
import br.com.project.control.datatype.HOperator;
import br.com.project.control.entity.pojo.Activity;
import br.com.project.control.factory.hql.HqlFactoryList;

public class ActivityDAO extends OperationsDAO<Activity> {
    public ActivityDAO() {
    }

    public ActivityDAO(Activity entity) {
        super(entity);
    }

    public ActivityDAO(int num) {
        super(num);
    }

    public ActivityDAO(String field, HOperator operation, String value) {
        super(field, operation, value);
    }

    public ActivityDAO(String field, HOperator operation, int num) {
        super(field, operation, num);
    }
}
