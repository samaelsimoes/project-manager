package br.com.project.control.daoconnect;


import br.com.project.control.dao.OperationsDAO;
import br.com.project.control.datatype.HOperator;
import br.com.project.control.entity.pojo.Project;

public class ProjectDAO extends OperationsDAO<Project> {
    public ProjectDAO() {
    }

    public ProjectDAO(Project entity) {
        super(entity);
    }

    public ProjectDAO(int num) {
        super(num);
    }

    public ProjectDAO(String field, HOperator operation, String value) {
        super(field, operation, value);
    }

    public ProjectDAO(String field, HOperator operation, int num) {
        super(field, operation, num);
    }
}
