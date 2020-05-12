package br.com.project.control.service;

import java.util.List;

import br.com.project.control.daoconnect.UserDAO;
import br.com.project.control.entity.pojo.User;
import br.com.project.control.exception.GlobalException;
import br.com.project.control.interfaces.UserServiceInterface;

public class UserService implements UserServiceInterface<User> {

    protected User User;
    protected List<User> Users;
    protected String valorStr;
    protected int num;

    public UserService() {
    }

    public UserService(User User) {
        this.User = User;
    }

    public UserService(String valorStr) {
        this.valorStr = valorStr;
    }

    public UserService(int num) {
        this.num = num;
    }

    public void setSenha(){
        if(!this.User.getPassword().equals(""))
            this.User.setPassword("*****");
        else
            this.User.setPassword("");
    }

    public void setSenhas(){
        for(User User: this.Users){
            if(!User.getPassword().equals(null) && !User.getPassword().equals(""))
                User.setPassword("*****");
            else
                User.setPassword("");
        }
    }
    public void adicionar()  throws GlobalException {
        new UserDAO(this.User).persist();
    }
}