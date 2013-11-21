/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package com.syamka.hibernate.entity.tariff;

import com.syamka.hibernate.entity.tariff.calculation.TariffPercentCalculation;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * <h3></h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 21.11.13</p>
 */
@Entity(name = "rko_tariff")
@DiscriminatorValue(value = "RKO")
public class RkoTariff extends Tariff<TariffPercentCalculation> {
}
