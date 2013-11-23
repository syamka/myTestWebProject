package com.syamka.hibernate.entity.tariff.percent;

import com.syamka.hibernate.entity.tariff.Tariff;
import com.syamka.hibernate.entity.tariff.calculation.TariffPercentCalculation;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Title: Суперкласс для тарифов с расчетом как процент от суммы
 * Description:
 * <p/>
 * User: valentina
 * Date: 23.11.13
 * Time: 19:07
 */
@MappedSuperclass
public class PercentTariff extends Tariff<TariffPercentCalculation> {

    @ManyToOne(targetEntity = TariffPercentCalculation.class)
    @JoinColumn(name = "calculation_id")
    protected TariffPercentCalculation calculation;

    public TariffPercentCalculation getCalculation() {
        return calculation;
    }

    public void setCalculation(TariffPercentCalculation calculation) {
        this.calculation = calculation;
    }

    @Override
    public String toString() {
        return calculation.toString();
    }
}
