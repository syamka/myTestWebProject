package com.syamka.hibernate.entity.tariff.fix;

import com.syamka.hibernate.entity.tariff.Tariff;
import com.syamka.hibernate.entity.tariff.calculation.TariffFixCalculation;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Title: Суперкласс для тарифов с фиксированной стоимостью
 * Description:
 * <p/>
 * User: valentina
 * Date: 23.11.13
 * Time: 19:07
 */
@MappedSuperclass
public class FixTariff extends Tariff<TariffFixCalculation> {

    @ManyToOne(targetEntity = TariffFixCalculation.class)
    @JoinColumn(name = "calculation_id")
    protected TariffFixCalculation calculation;

    public TariffFixCalculation getCalculation() {
        return calculation;
    }

    public void setCalculation(TariffFixCalculation calculation) {
        this.calculation = calculation;
    }

    @Override
    public String toString() {
        return calculation.toString();
    }
}
