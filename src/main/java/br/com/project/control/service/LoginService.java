package br.com.project.control.service;

import br.com.project.control.daoconnect.UserDAO;
import br.com.project.control.datatype.HOperator;
import br.com.project.control.entity.pojo.User;
import br.com.project.control.exception.GlobalException;
import br.com.project.control.factory.hql.HqlFactoryList;
import br.com.project.control.security.CripMD5;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class LoginService {

    private List<String> credenciais;

    public LoginService(List<String> credenciais) {
        this.credenciais = credenciais;
    }

    public User validateUser() throws GlobalException, UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            List<User> result = null;
            User user = null;

            CripMD5 cripmd5 = new CripMD5();
            String unraveling = cripmd5.decode64(credenciais.get(1));
            String hashd5 = CripMD5.encrypt(unraveling);

            HqlFactoryList<User> hqlFactoryList = new HqlFactoryList<User>();
            UserDAO userDAO = new UserDAO(hqlFactoryList);
            userDAO.setFindParams("user", HOperator.EQUALS,
                    credenciais.get(1));
            userDAO.setFindParams("password", HOperator.EQUALS, hashd5);
            userDAO.setFindParams("status", HOperator.EQUALS, "1");
            userDAO.setAnd();
            result = userDAO.findGenericAND();

            if (result != null && !result.isEmpty())
                user = result.get(0);
            else
                return null;

            if (!user.getPassword().equals(null) && !user.getPassword().equals("")) {
                user.setPassword("*****");
            } else {
                user.setPassword("");
            }

            return user;
        } catch (GlobalException | NoSuchAlgorithmException
                | UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}
