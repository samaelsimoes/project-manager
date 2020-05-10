package br.com.project.control.daoconnect;

import br.com.project.control.dao.OperationsDAO;

import br.com.project.control.entity.pojo.User;
import br.com.project.control.factory.hql.HqlFactoryList;

public class UserDAO extends OperationsDAO<User> {
    public UserDAO() {
        super();
    }

    public UserDAO(int num) {
        super.num = num;
    }

    public UserDAO(User user) {
        super.entity = user;
    }

    public UserDAO(HqlFactoryList<User> hqlFactoryList) {
        this.hqlFactoryList = hqlFactoryList;
    }

}
