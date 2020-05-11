package br.com.project.control.dao;

import br.com.project.control.datatype.HOperator;
import br.com.project.control.exception.GlobalException;
import br.com.project.control.factory.hql.HqlFactory;
import br.com.project.control.factory.hql.HqlFactoryList;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

public class OperationsDAO<T> extends ConnectionDAO<T>{

    protected T entity;
    protected int num;
    protected List<String> values = new ArrayList<String>();
    protected List<String> fields = new ArrayList<String>();
    protected List<Integer> nums = new ArrayList<Integer>();
    protected List<HOperator> operations = new ArrayList<HOperator>();
    protected HqlFactoryList<T> hqlFactoryList;
    protected List<T> listT = new ArrayList<T>();
    protected List<String> listStr = new ArrayList<String>();
    protected HOperator operation;
    protected String field;
    protected String value;
    protected int count = 0;
    protected String select = null;
    protected String queryStr=null;
    protected String queryOp= null;
    protected String queryAnd= null;
    protected Query query = null;

    public OperationsDAO(){

    }

    public OperationsDAO(T entity){
        this.entity = entity;
    }

    public OperationsDAO(int num){
        this.num = num;
    }


    public OperationsDAO(String field, HOperator operation, String value){
        this.field = field;
        this.operation = operation;
        this.value = value;
    }

    public OperationsDAO(String field, HOperator operation, int num){
        this.field = field;
        this.operation = operation;
        this.num = num;
    }

    public void setEntity(T entity){
        this.entity = entity;
    }

    public void persist(){
        em.getTransaction().begin();
        em.persist(this.entity);
        em.getTransaction().commit();
    }

    public void setFindParams(String field, HOperator operation, List<String> listString){
        this.fields.add(field);
        this.operations.add(operation);
        this.listStr = listString;
    }

    public void setFindParams(String field, HOperator operation, String value){
        this.fields.add(field);
        this.operations.add(operation);
        this.values.add(value);
    }

    public void setFindParams(String field, HOperator operation, int num){
        this.fields.add(field);
        this.operations.add(operation);
        this.nums.add(num);
    }

    public void getSelectString()throws GlobalException {
        if((!this.nums.isEmpty() || this.nums != null) && (this.values.isEmpty() || this.values == null))
            this.queryOp = this.hqlFactoryList.getRawOperationQuery(this.operations.get(count), this.nums.get(count));
        if((this.nums.isEmpty() || this.nums == null) && (!this.values.isEmpty() || this.values != null))
            this.queryOp = this.hqlFactoryList.getRawOperationQuery(this.operations.get(count), this.values.get(count));
        this.select = this.hqlFactoryList.getSelect(getEntityName(), this.fields.get(0))+queryOp;
        this.count++;
    }

    public void setAnds() throws GlobalException{

    }

    public void setQuery(){
        this.queryStr = select+queryOp;
        this.query = super.getQuery(queryStr);
    }

    public T findId(){
        Class<T> classe = getEmClass();
        T obj = em.find(classe, this.num);
        return obj;
    }

    public T find(){
        Class<T> classe = getEmClass();
        T obj = em.find(classe, this.entity);
        return obj;
    }

    public void setAnd() throws GlobalException{
        if((!this.nums.isEmpty() || this.nums != null) && (this.values.isEmpty() || this.values == null))
            this.queryStr += hqlFactoryList.getAnd(this.fields.get(count),this.operations.get(count), this.nums.get(count));
        if((this.nums.isEmpty() || this.nums == null) && (!this.values.isEmpty() || this.values != null))
            this.queryStr += hqlFactoryList.getAnd(this.fields.get(count),this.operations.get(count), this.values.get(count));

    }

    public List<T> findGeneric() throws GlobalException{
        @SuppressWarnings("unchecked")
        List<T> list = this.query.getResultList();
        return list;
    }

    public List<T> findGeneric(String field, HOperator operation, String value) throws GlobalException{
        HqlFactory hqlFactory = new HqlFactory();
        this.select = hqlFactory.getSelect(getEntityName(), field);
        this.queryStr = hqlFactory.getQuery(select, operation, value);
        this.query = super.getQuery(queryStr);
        @SuppressWarnings("unchecked")
        List<T> list = this.query.getResultList();
        return list;
    }

    public T findGenericObj(String field, HOperator operation, String value) throws GlobalException{
        HqlFactory hqlFactory = new HqlFactory();
        this.select = hqlFactory.getSelect(getEntityName(), field);
        this.queryStr = hqlFactory.getQuery(select, operation, value);
        this.query = super.getQuery(queryStr);
        @SuppressWarnings("unchecked")
        List<T> list = this.query.getResultList();
        T obj = list.get(0);
        return obj;
    }

    public List<T> findGenericAND() throws GlobalException{
        int contIt = 1;
        getSelectString();
        int listaSize = this.values.size()-1;
        this.queryStr = this.select;
        while(contIt <= listaSize){

            if(contIt <= listaSize)
                this.queryStr+=" AND ";
            this.queryStr+=this.fields.get(contIt)+" = '"+this.values.get(contIt)+"'";
            contIt++;

        }
        Query query = super.getQuery(queryStr);
        @SuppressWarnings("unchecked")
        List<T> list = query.getResultList();
        return list;
    }

    public List<T> findGenericInt(String field, HOperator operation, int value) throws GlobalException{
        HqlFactory hqlFactory = new HqlFactory();
        String select = hqlFactory.getSelect(getEntityName(), field);
        String queryStr = hqlFactory.getQuery(select, operation, value);
        Query query = super.getQuery(queryStr);
        @SuppressWarnings("unchecked")
        List<T> list = query.getResultList();
        return list;
    }

    public List<T> findGenericBetween() throws GlobalException{
        int contIt = 0;
        getSelectString();
        int listaSize = this.listStr.size();
        this.queryStr = this.select+" AND "+this.fields.get(this.count)+" BETWEEN ";
        for(String val: this.listStr){
            this.queryStr+="'"+val+"'";
            contIt++;
            if(contIt < listaSize)
                this.queryStr+=" AND ";
        }
        Query query = super.getQuery(queryStr);
        @SuppressWarnings("unchecked")
        List<T> list = query.getResultList();
        return list;
    }

    public void update(){
        em.getTransaction().begin();
        em.merge(this.entity);
        em.getTransaction().commit();
    }

    public void delete(){
        Class<T> classe = getEmClass();
        T obj = em.find(classe, this.num);
        em.getTransaction().begin();
        em.remove(obj);
        em.getTransaction().commit();
    }

}
