package com.syamka.hibernate.entity.inheritance;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Title:
 * Description:
 * <p/>
 * TODO почему @Table(name="single_test") вызывает ошибку 'table...not mapped' при выборке
 *
 * User: valentina
 * Date: 11.11.13
 * Time: 23:56
 */
@Entity(name="single_test")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
public class BaseTable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name="title")
    protected String title;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
