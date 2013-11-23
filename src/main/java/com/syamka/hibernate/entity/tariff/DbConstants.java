/*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package com.syamka.hibernate.entity.tariff;

/**
 * <h3>Константы для persistence</h3>
 * <p></p>
 * <p>Author: predtechenskaya (predtechenskaya@i-teco.ru)</p>
 * <p>Date: 21.11.13</p>
 */
public interface DbConstants {

    public static final String FIX_CALCULATION_TABLE_NAME = "tariff_fix_calc";

    public static final String PERCENT_CALCULATION_TABLE_NAME = "tariff_percent_calc";

    public static final String WEIGHT_CALCULATION_TABLE_NAME = "tariff_weight_calc";

    public static final String WEIGHT_ITEM_TABLE_NAME = "tariff_weight_item";

    public static final String PERCENT_ITEM_TABLE_NAME = "tariff_percent_item";

    public static final String TARIFF_TABLE_NAME = "tariff";

    public static final String TARIFF_SCALE_TABLE_NAME = "tariff_scale";

    public static final String ESCAPE_FROM = "`from`";
    public static final String ESCAPE_TO = "`to`";

}
