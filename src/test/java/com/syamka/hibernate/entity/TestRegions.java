package com.syamka.hibernate.entity;

import org.junit.Test;

import javax.persistence.*;
import java.util.List;

/**
 * Title: Тестовый класс для маппинга регионов
 * Description: ...
 *
 * User: valentina
 * Date: 01.11.13
 * Time: 18:12
 */
public class TestRegions extends TestEntities {

    @Test
    public void test(){
        insertRandom(5);
        getAll();
        getEntityManager().close();
    }


    protected void insertRandom(int cnt){
        getEntityManager().getTransaction().begin();
        for(int i=0;i<cnt;i++){
            Region region = new Region();
            region.setTitle("Регион № " + Math.round(Math.random() * 1000));
            getEntityManager().persist(region);
        }
        getEntityManager().getTransaction().commit();
    }


    public void getAll(){
        Query query = getEntityManager().createQuery("SELECT r FROM regions r");
        for(Region region: (List<Region>)query.getResultList()){
            System.out.println(region);
        }
    }

}
