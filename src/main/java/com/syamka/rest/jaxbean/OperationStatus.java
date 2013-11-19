package com.syamka.rest.jaxbean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Title: JAX-bean для передачи статуса операции
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

    public OperationStatus(boolean success, String message, int id){
        this(success, message);
        this.id = id;
    }

    public boolean success;
    public String message;
    public int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
