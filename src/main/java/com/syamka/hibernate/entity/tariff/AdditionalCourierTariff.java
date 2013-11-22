/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package com.syamka.hibernate.entity.tariff;

import com.syamka.hibernate.entity.tariff.calculation.TariffWeightCalculation;

import javax.persistence.*;

/**
 * <h3></h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 21.11.13</p>
 */
@Entity(name = "additional_courier_tariff")
@DiscriminatorValue(value = "ADDITIONAL_COURIER")
public class AdditionalCourierTariff extends Tariff<TariffWeightCalculation> {
}
