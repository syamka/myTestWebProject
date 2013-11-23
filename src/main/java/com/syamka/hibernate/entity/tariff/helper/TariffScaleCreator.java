package com.syamka.hibernate.entity.tariff.helper;

import com.syamka.hibernate.Util;
import com.syamka.hibernate.entity.tariff.Tariff;
import com.syamka.hibernate.entity.tariff.TariffScale;

import javax.persistence.Query;
import java.util.List;

/**
 * Title: Класс для создания новой тарифной сетки
 * Description: Поскольку тарифная сетка не может существовать "пустой", то ее создание подразумевает также
 * создание всех возможных тарифов по существующим городам.
 * Как это было сделано в ТД, осуществляем копирование другой ТС.
 * <p/>
 * User: valentina
 * Date: 23.11.13
 * Time: 20:17
 */
public class TariffScaleCreator {

    public TariffScaleCreator(){
        original = findFirst();
    }

    protected TariffScale original;

    public TariffScale getOriginal() {
        return original;
    }

    public void setOriginal(TariffScale original) {
        this.original = original;
    }

    public void createTariffScale(TariffScale created){

    }


    protected TariffScale findFirst(){
        Query query = Util.getEm().createQuery("SELECT t FROM tariff_scale t ORDER BY id DESC")
                                  .setMaxResults(1);
        return (TariffScale) query.getSingleResult();
    }



}
