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
@Entity(name= DbConstants.WEIGHT_ITEM_TABLE_NAME)
public class TariffWeightItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private BigInteger id;

    public BigInteger getId() {
        return id;
    }

    @ManyToOne(targetEntity = TariffWeightCalculation.class)
    @JoinColumn(name="tariff_id")
    protected TariffWeightCalculation tariff;

    public TariffWeightCalculation getTariff() {
        return tariff;
    }

    public void setTariff(TariffWeightCalculation tariff) {
        this.tariff = tariff;
    }

    @Column( name= DbConstants.ESCAPE_FROM)
    protected double from;
    @Column(name= DbConstants.ESCAPE_TO)
    protected double to;
    protected double price;
    protected double next;
    protected Type type;

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getNext() {
        return next;
    }

    public void setNext(double next) {
        this.next = next;
    }


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type{
        FIX_SUM, BY_WEIGHT
    }

    @Override
    public String toString() {
        return "["+getId()+"] " + from + " - " + to + ": " + price + " (" + type.toString() + ")";
    }
}
