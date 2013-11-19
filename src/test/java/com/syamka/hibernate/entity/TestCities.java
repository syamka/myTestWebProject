/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package com.syamka.hibernate.entity;

import org.junit.Test;

import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * <h3></h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 19.11.13</p>
 */
public class TestCities extends TestEntities {

    public void test(){
        //insertRandom(30);
        getAll();
        getEntityManager().close();
    }

    protected void insertRandom(int cnt){
        getEntityManager().getTransaction().begin();
        for(int i=0;i<cnt;i++){
            City city = new City();
            city.setTitle("Город № " + Math.round(Math.random() * 1000));
            //считаем, что 10 регионов наколбасили
            city.setRegion(getEntityManager().find(Region.class, BigInteger.valueOf(Math.round(Math.random() * 9))));
            getEntityManager().persist(city);
        }
        getEntityManager().getTransaction().commit();
    }

    public void getAll(){
        Query query = getEntityManager().createQuery("SELECT c FROM cities c");
        for(City city: (List<City>)query.getResultList()){
            System.out.println(city);
        }
    }

    @Test
    public void testQuery(){
        String queryStr = "SELECT c FROM cities c WHERE c.region=:region ORDER BY title ASC";
        Query query = getEntityManager().createQuery(queryStr)
                                        .setParameter("region", getEntityManager().find(Region.class, BigInteger.valueOf(2)));

        for(City city: (List<City>)query.getResultList()){
            System.out.println(city);
        }
    }


}
