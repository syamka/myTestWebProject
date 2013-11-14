package com.syamka.rest.jaxbean.item;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Title: Базовый класс для передачи в JSON различных объектов
 * Description:
 * <p/>
 * User: valentina
 * Date: 02.11.13
 * Time: 23:33
 *
 *
 * TODO Решение @XmlSeeAlso - идиотское, оно мне НЕ НРАВИТСЯ
 * нужно подумать, как верно разрулить ситуацию, когда JAXB не может в рантайме определить реальный тип генерика.
 * В данном случае придется прописывать сюда ВСЕ возможные варианты +
 * в результирующие данные попадают дополнительные лишние данные
 */
@XmlRootElement
@XmlSeeAlso({
    RegionItem.class,
    CityItem.class,
    TariffScaleItem.class
})
public class JqgridResultItem {}
