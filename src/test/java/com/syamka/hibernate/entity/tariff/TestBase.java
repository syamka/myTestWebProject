/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package com.syamka.hibernate.entity.tariff;


import com.syamka.hibernate.entity.TestEntities;
import com.syamka.hibernate.entity.tariff.calculation.*;
import org.junit.Test;

import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

/**
 * <h3></h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 20.11.13</p>
 */
public class TestBase extends TestEntities {

    @Test
    public void test(){
        // generateCalculations();
        printFix();
        printPercent();
        printWeight();
    }


    private void generateCalculations(){
        getEntityManager().getTransaction().begin();
        for(int i=0; i < 15; i++){
            getEntityManager().persist(getRandomFixed());
            getEntityManager().persist(getRandomPercent());
            getEntityManager().persist(getRandomWeight());
        }
        getEntityManager().getTransaction().commit();
    }

    private void printFix(){
        Query query  = getEntityManager().createQuery("SELECT f FROM tariff_fix_calc f");
        for(TariffFixCalculation c: (List<TariffFixCalculation>)query.getResultList())
            System.out.println(c);
    }


    private void printWeight(){
        Query query  = getEntityManager().createQuery("SELECT f FROM tariff_weight_calc f");
        for(TariffWeightCalculation c: (List<TariffWeightCalculation>)query.getResultList())
            System.out.println(c);
    }

    private void printPercent(){
        Query query  = getEntityManager().createQuery("SELECT f FROM tariff_percent_calc f");
        for(TariffPercentCalculation c: (List<TariffPercentCalculation>)query.getResultList())
            System.out.println(c);
    }

    private TariffFixCalculation getRandomFixed(){
        TariffFixCalculation calculation = new TariffFixCalculation();
        calculation.setPrice(Math.random() * 1000);
        return calculation;
    }


    private TariffWeightCalculation getRandomWeight(){
        List<TariffWeightItem> list = new LinkedList<TariffWeightItem>();
        for(int i=0; i<3; i++){
            TariffWeightItem item = new TariffWeightItem();
            item.setFrom(Math.random() * 100);
            item.setTo(item.getFrom() + Math.random() * 50);
            item.setPrice(Math.random() * 100);
            item.setType(TariffWeightItem.Type.FIX_SUM);

            list.add(item);
        }
        TariffWeightItem item = new TariffWeightItem();
        item.setFrom(Math.random() * 100);
        item.setTo(item.getFrom() + Math.random() * 50);
        item.setPrice(Math.random() * 100);
        item.setNext(Math.random() * 10);
        item.setType(TariffWeightItem.Type.BY_WEIGHT);
        list.add(item);

        TariffWeightCalculation calculation = new TariffWeightCalculation();
        calculation.setItems(list);
        return calculation;
    }

    private TariffPercentCalculation getRandomPercent(){
        List<TariffPercentItem> list = new LinkedList<TariffPercentItem>();

        for(int i=0; i<3; i++){
            TariffPercentItem item = new TariffPercentItem();
            item.setFrom(Math.random() * 100);
            item.setTo(item.getFrom() + Math.random() * 50);
            item.setPercent(Math.random() * 100);

            list.add(item);
        }
        TariffPercentCalculation calculation = new TariffPercentCalculation();
        calculation.setItems(list);
        return calculation;

    }


}
