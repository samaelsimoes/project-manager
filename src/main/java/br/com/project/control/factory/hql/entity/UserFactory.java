package br.com.project.control.factory.hql.entity;

import br.com.project.control.entity.pojo.Adm;
import br.com.project.control.entity.pojo.User;
import br.com.project.control.exception.GlobalException;
import br.com.project.control.factory.hql.ObjMapper;
import jdk.nashorn.internal.ir.ObjectNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserFactory extends ObjMapper {
    private List<User> users = new ArrayList<User>();
    private Class<?> userClass;
    private ObjectNode objNode;

    static final HashMap<Integer, Class<?>> USER_CLASS =  new HashMap<Integer, Class<?>>();
    static{
        USER_CLASS.put(0, null);
        USER_CLASS.put(1, Adm.class);
    }

    public UserFactory(String userStr) throws GlobalException {

        int type = 0;
        try {

            objNode = getObject().readValue(userStr, ObjectNode.class);
            if (objNode != null)
                type = 1;
                        //objNode.get("type").asInt(); ADM
            else
                throw new GlobalException(
                        "Falha ao receber o atributo tipo de Usuario");

            if (type != 0) {

                this.userClass = USER_CLASS.get(type);

                if (this.userClass != null) {
                    User obj = (User) getObject().readValue(userStr,
                            this.userClass);
                    this.users.add(obj);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new GlobalException("Erro de factory na classe Usuario");
        }
    }

    public UserFactory(int type){
        this.userClass = USER_CLASS.get(type);
    }

    public List<User> getList() {
        return users;
    }

    public void setUser(List<User> users) {
        this.users = users;
    }

    public User getUser() {
        return users.get(0);
    }

    public Class<?> getClasse(){
        return this.userClass;
    }
}