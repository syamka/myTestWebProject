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
import com.syamka.hibernate.entity.tariff.calculation.TariffPercentCalculation;
import com.syamka.hibernate.entity.tariff.calculation.TariffPercentItem;
import com.syamka.hibernate.entity.tariff.calculation.TariffWeightCalculation;
import com.syamka.hibernate.entity.tariff.calculation.TariffWeightItem;

import java.util.LinkedList;
import java.util.List;

/**
 * <h3></h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 20.11.13</p>
 */
public class TestBase extends TestEntities {


    private void generateCalculations(){
         //наколбасить тарифов пользуясь методами ниже
    }


    private TariffWeightCalculation getRandomWeight(){
        List<TariffWeightItem> list = new LinkedList<TariffWeightItem>();
        for(int i=0; i<3; i++){
            TariffWeightItem item = new TariffWeightItem();
            item.setFrom(Math.random() * 100);
            item.setTo(item.getTo() + Math.random() * 50);
            item.setPrice(Math.random() * 100);
            item.setType(TariffWeightItem.Type.FIX_SUM);

            list.add(item);
        }
        TariffWeightItem item = new TariffWeightItem();
        item.setFrom(Math.random() * 100);
        item.setTo(item.getTo() + Math.random() * 50);
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
            item.setTo(item.getTo() + Math.random() * 50);
            item.setPercent(Math.random() * 100);

            list.add(item);
        }
        TariffPercentItem item = new TariffPercentItem();
        item.setFrom(Math.random() * 100);
        item.setTo(item.getTo() + Math.random() * 50);
        item.setPercent(Math.random() * 100);
        list.add(item);

        TariffPercentCalculation calculation = new TariffPercentCalculation();
        calculation.setItems(list);
        return calculation;

    }


}
