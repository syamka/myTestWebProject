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
import com.syamka.hibernate.entity.tariff.calculation.TariffCalculation;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * <h3></h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 21.11.13</p>
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "tariff")
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING)
public abstract class Tariff<T extends TariffCalculation> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    public BigInteger getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "city_id")
    protected City city;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "calculation_id")
    protected T calculation;

    @ManyToOne
    @JoinColumn(name = "tariff_scale_id")
    protected TariffScale tariffScale;

    public T getCalculation() {
        return calculation;
    }

    public void setCalculation(T calculation) {
        this.calculation = calculation;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public TariffScale getTariffScale() {
        return tariffScale;
    }

    public void setTariffScale(TariffScale tariffScale) {
        this.tariffScale = tariffScale;
    }
}
