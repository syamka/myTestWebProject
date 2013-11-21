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
    public void createFirst(){
        //дефолтовый фиксированный тариф
        TariffFixCalculation fix = new TariffFixCalculation();
        fix.setPrice(200);

        //собираем весовой тариф...
        List<TariffWeightItem> wlist = new LinkedList<TariffWeightItem>();
        TariffWeightItem witem = new TariffWeightItem();
        witem.setFrom(0);
        witem.setTo(5);
        witem.setPrice(200);
        witem.setType(TariffWeightItem.Type.FIX_SUM);
        wlist.add(witem);

        witem = new TariffWeightItem();
        witem.setFrom(5);
        witem.setTo(10);
        witem.setPrice(100);
        witem.setType(TariffWeightItem.Type.FIX_SUM);
        wlist.add(witem);

        witem = new TariffWeightItem();
        witem.setFrom(10);
        witem.setTo(20);
        witem.setPrice(50);
        witem.setType(TariffWeightItem.Type.FIX_SUM);
        wlist.add(witem);

        witem = new TariffWeightItem();
        witem.setFrom(20);
        witem.setPrice(5);
        witem.setType(TariffWeightItem.Type.BY_WEIGHT);
        wlist.add(witem);

        //дефолтовый весовой тариф
        TariffWeightCalculation weight = new TariffWeightCalculation();
        weight.setItems(wlist);

        //собираем процентный тариф...
        List<TariffPercentItem> plist = new LinkedList<TariffPercentItem>();
        TariffPercentItem pitem = new TariffPercentItem();
        pitem.setFrom(0);
        pitem.setTo(1000);
        pitem.setPercent(5);
        plist.add(pitem);

        pitem = new TariffPercentItem();
        pitem.setFrom(1000);
        pitem.setTo(10000);
        pitem.setPercent(3);
        plist.add(pitem);

        pitem = new TariffPercentItem();
        pitem.setFrom(10000);
        pitem.setPercent(1);
        plist.add(pitem);

        //дефолтовый процентный тариф
        TariffPercentCalculation percent = new TariffPercentCalculation();
        percent.setItems(plist);

        List<CourierTariff> courierTariffs = new LinkedList<CourierTariff>();
        List<PickupTariff> pickupTariffs = new LinkedList<PickupTariff>();
        List<RkoTariff> rkoTariffs = new LinkedList<RkoTariff>();
        List<ReturnTariff> returnTariffs = new LinkedList<ReturnTariff>();
        List<AdditionalCourierTariff> additionalCourierTariffs = new LinkedList<AdditionalCourierTariff>();
        List<InsuranceTariff> insuranceTariffs = new LinkedList<InsuranceTariff>();

        for(City c: getAllCities()){
            CourierTariff courierTariff = new CourierTariff();
            courierTariff.setCity(c);
            courierTariff.setCalculation(weight);
            courierTariffs.add(courierTariff);

            PickupTariff pickupTariff = new PickupTariff();
            pickupTariff.setCity(c);
            pickupTariff.setCalculation(weight);
            pickupTariffs.add(pickupTariff);

            RkoTariff rkoTariff = new RkoTariff();
            rkoTariff.setCity(c);
            rkoTariff.setCalculation(percent);
            rkoTariffs.add(rkoTariff);

            ReturnTariff returnTariff = new ReturnTariff();
            returnTariff.setCity(c);
            returnTariff.setCalculation(weight);
            returnTariffs.add(returnTariff);

            AdditionalCourierTariff additionalCourierTariff = new AdditionalCourierTariff();
            additionalCourierTariff.setCity(c);
            additionalCourierTariff.setCalculation(weight);
            additionalCourierTariffs.add(additionalCourierTariff);

            InsuranceTariff insuranceTariff = new InsuranceTariff();
            insuranceTariff.setCity(c);
            insuranceTariff.setCalculation(fix);
            insuranceTariffs.add(insuranceTariff);
        }

        TariffScale tariffScale = new TariffScale();
        tariffScale.setTitle("Основная");
        tariffScale.setDescription("Заполнена дефолтовыми значениями, нужна для функционирования системы");
        tariffScale.setInsuranceTariffs(insuranceTariffs);
        tariffScale.setAdditionalCourierTariffs(additionalCourierTariffs);
        tariffScale.setReturnTariffs(returnTariffs);
        tariffScale.setCourierTariffs(courierTariffs);
        tariffScale.setPickupTariffs(pickupTariffs);
        tariffScale.setRkoTariffs(rkoTariffs);

        getEntityManager().getTransaction().begin();
        getEntityManager().persist(tariffScale);
        getEntityManager().getTransaction().commit();
    }

    private List<City> getAllCities(){
        Query query = getEntityManager().createQuery("SELECT c FROM cities c");
        return (List<City>) query.getResultList();

    }

}
