package com.syamka.hibernate.rest.jaxbean;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Title:
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
    CityItem.class
})
public class JqgridResultItem {}
