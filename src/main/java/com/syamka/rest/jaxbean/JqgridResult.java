package com.syamka.rest.jaxbean;

import com.syamka.rest.jaxbean.item.JqgridResultItem;

import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Title: Базовый класс для отдачи результатов jqGrid.
 * Description: Включает информационные поля типа page, records...
 * <p/>
 * User: valentina
 * Date: 02.11.13
 * Time: 23:28
 *
 * Важно !!!
 * JAXB (наверное, она) НЕ ПРИЕМЛЕТ геттеры, см. ошибка:
 * Caused by: com.sun.xml.bind.v2.runtime.IllegalAnnotationsException: 3 counts of IllegalAnnotationExceptions
 * Class has two properties of the same name "records"
 * this problem is related to the following location:
 * at public int com.syamka.rest.jaxbean.JqgridResult.getRecords()
 * at com.syamka.rest.jaxbean.JqgridResult
 * this problem is related to the following location:
 * at public int com.syamka.rest.jaxbean.JqgridResult.records
 * at com.syamka.rest.jaxbean.JqgridResult
 *
 * TODO понять, почему
 *
 */
@XmlRootElement
public class JqgridResult<T extends JqgridResultItem> {

    public JqgridResult(){}

    public JqgridResult(int page, int records, int total, List<T> rows) {
        this.page = page;
        this.records = records;
        this.total = total;
        this.rows = rows;
    }

    public int page;
    public int records;
    public int total;

    //Закомментированное ниже - не работает. почему ?...
    //@XmlAnyElement(lax=true)
    public List<T> rows;

    public void setPage(int page) {
        this.page = page;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
