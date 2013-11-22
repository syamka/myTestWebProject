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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.List;

/**
 * <h3></h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 20.11.13</p>
 */
@Entity(name= DbConstants.WEIGHT_CALCULATION_TABLE_NAME)
public class TariffWeightCalculation extends TariffCalculation {

    @OneToMany(mappedBy = "tariff")
    @OrderBy(value = DbConstants.ESCAPE_FROM + " ASC")
    protected List<TariffWeightItem> items;

    public List<TariffWeightItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getId()).append("\n");
        for(TariffWeightItem item: items)
            sb.append(item.toString()).append("\n");

        return sb.toString();
    }
}
