package com.syamka.hibernate.entity;

import com.syamka.hibernate.entity.inheritance.BaseTable;
import com.syamka.hibernate.entity.inheritance.Table1;
import com.syamka.hibernate.entity.inheritance.Table2;
import org.junit.Test;

import javax.persistence.Query;
import java.util.List;

/**
 * Title:
 * Description:
 * <p/>
 * User: valentina
 * Date: 12.11.13
 * Time: 0:07
 */
public class TestInheritance extends TestEntities {

    @Test
    public void run(){
        //fill();
        getAll();
        getEntityManager().close();
    }

    public void fill(){

        getEntityManager().getTransaction().begin();

        Table1 t1 = new Table1();
        t1.setTitle("Тест 1");
        t1.setVar1("123456");

        getEntityManager().persist(t1);

        t1 = new Table1();
        t1.setTitle("Тест 12");
        t1.setVar1("трололо 123456");

        getEntityManager().persist(t1);

        t1 = new Table1();
        t1.setTitle("Тест 112");
        t1.setVar1("катрин 123456");

        getEntityManager().persist(t1);

        Table2 t2 = new Table2();
        t2.setTitle("Тест 2");
        t2.setVar2("123456");

        getEntityManager().persist(t2);

        t2 = new Table2();
        t2.setTitle("Тест 22");
        t2.setVar2("пиупиу 123456");

        getEntityManager().persist(t2);

        t2 = new Table2();
        t2.setTitle("Тест 222");
        t2.setVar2("катрина 123456");

        getEntityManager().persist(t2);

        getEntityManager().getTransaction().commit();
    }


    public void getAll(){
        Query query = getEntityManager().createQuery("SELECT r FROM single_test r");
        for(BaseTable t: (List<BaseTable>)query.getResultList()){
            System.out.println(t);
        }
    }
}
