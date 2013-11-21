/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package com.syamka.hibernate.entity.tariff.calculation;

import com.syamka.hibernate.entity.tariff.DbConstants;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * <h3></h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 20.11.13</p>
 */
@Entity(name= DbConstants.PERCENT_ITEM_TABLE_NAME)
public class TariffPercentItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private BigInteger id;

    public BigInteger getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name="tariff_id")
    protected TariffPercentCalculation tariff;

    public TariffPercentCalculation getTariff() {
        return tariff;
    }

    public void setTariff(TariffPercentCalculation tariff) {
        this.tariff = tariff;
    }

    @Column(name= DbConstants.ESCAPE_FROM)
    protected double from;
    @Column(name= DbConstants.ESCAPE_TO)
    protected double to;
    protected double percent;

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "["+getId()+"] " + from + " - " + to + ": " + percent + "%";
    }
}
