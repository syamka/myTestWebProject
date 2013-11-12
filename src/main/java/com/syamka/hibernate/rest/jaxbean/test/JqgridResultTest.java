package com.syamka.hibernate.rest.jaxbean.test;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.LinkedList;
import java.util.List;

/**
 * Title: Попытка создания JAXB - bean, обобщающего результаты для jqGrid (тестовая версия)
 * Description:
 * <p/>
 * User: valentina
 * Date: 02.11.13
 * Time: 18:52
 */
@XmlRootElement
public class JqgridResultTest<T extends JgridResultItem> {

    public JqgridResultTest(){}

    public JqgridResultTest(int rows, int row, int total, List<T> data){
        setRow(row);
        setRows(rows);
        setTotal(total);
        setData(data);
    }


    /**
     *  Реальные аттрибуты для результирующего JSON
     */

    //количество строк
    public int rows;
    //текущая строка
    public int row;
    //всего записей
    public int total;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }


    //непосредственно данные
    public List<T> data;

    public void setData(List<T> data){
        this.data = data;
    }

}
