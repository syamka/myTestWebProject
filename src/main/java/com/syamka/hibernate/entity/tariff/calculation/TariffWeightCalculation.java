/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package com.syamka.hibernate.entity.tariff.calculation;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * <h3></h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 20.11.13</p>
 */
@Entity(name="tariff_weight_calc")
public class TariffWeightCalculation extends TariffCalculation {

    @OneToMany(mappedBy = "tariff")
    protected List<TariffWeightItem> items;

    public List<TariffWeightItem> getItems() {
        return items;
    }

    public void setItems(List<TariffWeightItem> items) {
        this.items = items;
    }
}
