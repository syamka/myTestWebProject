/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package com.syamka.hibernate.entity.tariff;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

/**
 * <h3>Класс entity тарифной сетки</h3>
 * <p>
 *     Из-за того, что тарифы - полиморфны, возникает проблема, см. http://chriswongdevblog.blogspot.ru/2009/10/polymorphic-one-to-many-relationships.html
 *     То есть, мы НЕ МОЖЕМ использовать mappedBy и НЕ МОЖЕМ управлять связью ТС - тариф из ТС.
 *     Это в целом логично, т.к. в принципе нам не нужен список всех тарифов ТС, а только возможность получать нужный тариф по запросу.
 * </p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 21.11.13</p>
 */
@Entity(name= DbConstants.TARIFF_SCALE_TABLE_NAME)
public class TariffScale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    public BigInteger getId() {
        return id;
    }

    protected String title;
    protected String description;
    protected boolean main;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }

    public TariffScale(){}

}
