/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package com.syamka.hibernate.entity.tariff;

import com.syamka.hibernate.entity.City;
import com.syamka.hibernate.entity.TestEntities;
import com.syamka.hibernate.entity.tariff.calculation.*;
import org.junit.Test;

import javax.persistence.Query;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

/**
 * <h3></h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 21.11.13</p>
 */
public class TestTariffScales extends TestEntities{
    @Test
    public void getOne(){
        //City city = getEntityManager().find(City.class, 1);
        //TariffScale ts = getEntityManager().find(TariffScale.class, 15)
        BigInteger cityId = BigInteger.valueOf(1);
        BigInteger tsId = BigInteger.valueOf(15);

        Query query = getEntityManager().createQuery("SELECT t FROM insurance_tariff t WHERE city_id="+cityId+" AND tariff_scale_id="+tsId);
        System.out.println(query.getSingleResult());


    }

    public void createFirst(){
        getEntityManager().getTransaction().begin();

        //дефолтовый фиксированный тариф
        TariffFixCalculation fix = new TariffFixCalculation();
        fix.setPrice(200);
        getEntityManager().persist(fix);

        //дефолтовый весовой тариф
        TariffWeightCalculation weight = new TariffWeightCalculation();
        getEntityManager().persist(weight);

        //собираем весовой тариф...
        TariffWeightItem witem = new TariffWeightItem();
        witem.setFrom(0);
        witem.setTo(5);
        witem.setPrice(200);
        witem.setType(TariffWeightItem.Type.FIX_SUM);
        witem.setTariff(weight);
        getEntityManager().persist(witem);

        witem = new TariffWeightItem();
        witem.setFrom(5);
        witem.setTo(10);
        witem.setPrice(100);
        witem.setType(TariffWeightItem.Type.FIX_SUM);
        witem.setTariff(weight);
        getEntityManager().persist(witem);

        witem = new TariffWeightItem();
        witem.setFrom(10);
        witem.setTo(20);
        witem.setPrice(50);
        witem.setType(TariffWeightItem.Type.FIX_SUM);
        witem.setTariff(weight);
        getEntityManager().persist(witem);

        witem = new TariffWeightItem();
        witem.setFrom(20);
        witem.setPrice(5);
        witem.setType(TariffWeightItem.Type.BY_WEIGHT);
        witem.setTariff(weight);
        getEntityManager().persist(witem);


        //дефолтовый процентный тариф
        TariffPercentCalculation percent = new TariffPercentCalculation();
        getEntityManager().persist(percent);

        //собираем процентный тариф...
        TariffPercentItem pitem = new TariffPercentItem();
        pitem.setFrom(0);
        pitem.setTo(1000);
        pitem.setPercent(5);
        pitem.setTariff(percent);
        getEntityManager().persist(pitem);

        pitem = new TariffPercentItem();
        pitem.setFrom(1000);
        pitem.setTo(10000);
        pitem.setPercent(3);
        pitem.setTariff(percent);
        getEntityManager().persist(pitem);

        pitem = new TariffPercentItem();
        pitem.setFrom(10000);
        pitem.setPercent(1);
        pitem.setTariff(percent);
        getEntityManager().persist(pitem);

        //Создаем ТС (пустую пока)
        TariffScale tariffScale = new TariffScale();
        tariffScale.setTitle("Основная");
        tariffScale.setDescription("Заполнена дефолтовыми значениями, нужна для функционирования системы");

        getEntityManager().persist(tariffScale);

        for(City c: getAllCities()){
            Tariff[] wtariffs = {
                    new CourierTariff(),
                    new PickupTariff(),
                    new ReturnTariff(),
                    new AdditionalCourierTariff()
            };

            for(Tariff tariff: wtariffs){
                tariff.setCity(c);
                tariff.setTariffScale(tariffScale);
                tariff.setCalculation(weight);

                getEntityManager().persist(tariff);
            }

            RkoTariff rko = new RkoTariff();
            rko.setCity(c);
            rko.setCalculation(percent);
            rko.setTariffScale(tariffScale);
            getEntityManager().persist(rko);

            InsuranceTariff ins = new InsuranceTariff();
            ins.setCity(c);
            ins.setCalculation(fix);
            ins.setTariffScale(tariffScale);
            getEntityManager().persist(ins);
        }

        getEntityManager().getTransaction().commit();
    }

    private List<City> getAllCities(){
        Query query = getEntityManager().createQuery("SELECT c FROM cities c");
        return (List<City>) query.getResultList();

    }

}
