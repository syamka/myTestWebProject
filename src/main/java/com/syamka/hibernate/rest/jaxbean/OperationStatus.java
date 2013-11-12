package com.syamka.hibernate.rest.jaxbean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Title:
 * Description:
 * <p/>
 * User: valentina
 * Date: 02.11.13
 * Time: 23:55
 */
@XmlRootElement
public class OperationStatus {

    public OperationStatus() {
    }

    public OperationStatus(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean success;
    public String message;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
