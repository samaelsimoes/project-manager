package br.com.project.control.dao;

import java.lang.reflect.ParameterizedType;
import br.com.project.control.config.HConnect;

import javax.persistence.EntityManager;

public abstract class ConnectionDAO<T> extends HConnect implements GenericEntityDAO{
    protected EntityManager em;

    public ConnectionDAO(){
        em = getEntityManager();
    }

    public String getEntityName(){
        return getSuperClass().getSimpleName();
    }


    public Class<T> getEmClass(){
        Class<T> classe = getSuperClass();
        return classe;
    }

    public Class<T> getSuperClass(){
        ParameterizedType superclass = (ParameterizedType)getClass().getGenericSuperclass();
        @SuppressWarnings("unchecked")
        Class<T> classe = (Class<T>)superclass.getActualTypeArguments()[0];
        return classe;
    }

}
