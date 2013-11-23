package com.syamka.hibernate.entity.tariff.weight;

import com.syamka.hibernate.entity.tariff.Tariff;
import com.syamka.hibernate.entity.tariff.calculation.TariffWeightCalculation;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Title: Суперкласс для тарифов по весу
 * Description:
 * <p/>
 * User: valentina
 * Date: 23.11.13
 * Time: 19:06
 */
@MappedSuperclass
public class WeightTariff extends Tariff<TariffWeightCalculation> {

    @ManyToOne(targetEntity = TariffWeightCalculation.class)
    @JoinColumn(name = "calculation_id")
    protected TariffWeightCalculation calculation;

    public TariffWeightCalculation getCalculation() {
        return calculation;
    }

    public void setCalculation(TariffWeightCalculation calculation) {
        this.calculation = calculation;
    }

    @Override
    public String toString() {
        return calculation.toString();
    }
}
