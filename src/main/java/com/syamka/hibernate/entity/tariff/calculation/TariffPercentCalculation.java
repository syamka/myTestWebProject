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
import java.util.List;

/**
 * <h3></h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 20.11.13</p>
 */
@Entity(name= DbConstants.PERCENT_CALCULATION_TABLE_NAME)
public class TariffPercentCalculation extends TariffCalculation {

    @OneToMany(mappedBy = "tariff")
    protected List<TariffPercentItem> items;

    public List<TariffPercentItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getId()).append("\n");
        for(TariffPercentItem item: items)
            sb.append(item.toString()).append("\n");

        return sb.toString();
    }
}
