/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package com.syamka.hibernate.entity.tariff.calculation;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * <h3></h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 20.11.13</p>
 */
@Entity(name="tariff_percent_item")
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

    protected double from;
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
}
