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
 * <h3><Основной класс тарифа/h3>
 * <p>
 *     Является Entity, однако абстрактным.
 *     Из-за особенностей hibernate и его неумения обрабатывать generic типизацию столбца
 *     (что, в принципе, понятно, т.к. в рантайме происходит стирание типизации), столбец calculation
 *     определен в потомках, а не здесь.
 *     Другой вариант реализации этой задачи - сделать TariffCalculation не TABLE_PER_CLASS, а
 *     JOINED (то есть, ввести доп. таблицу для того, чтобы у каждого расчета по тарифу был уникальный id).
 *     Это позволило бы избавиться от классов FixedTariff, WeightTariff, PercentTariff, которые нужну только
 *     для явной типизации расчета.
 *
 *     Если убрать параметризацию, то все тарифы смогут быть любого типа, т.е., например:
 *     тариф на курьерку в Москве - по весу, в Питере - по проценту от суммы
 * </p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 21.11.13</p>
 */
@NamedQuery(name = "Tariff.get",
            query = "SELECT t FROM tariff t " +
                    //Поскольку здесь довольно смешная поддержка полиморфизма, в class нужно передавать DiscriminatorValue.
                    //Чтобы его не хардкодить например можно сделать такой жуткий финт:
                    // .setParameter("class", InsuranceTariff.class.getAnnotation(DiscriminatorValue.class).value())
                    "WHERE city=:city AND tariffScale=:tariffScale AND t.class=:class")

@Entity(name = DbConstants.TARIFF_TABLE_NAME)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING)
public abstract class Tariff<T extends TariffCalculation> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    public BigInteger getId() {
        return id;
    }

    //city и tariffScale имеют LAZY-загрузку, чтобы не тянуть постоянно ненужную информацию

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    protected City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tariff_scale_id")
    protected TariffScale tariffScale;

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
