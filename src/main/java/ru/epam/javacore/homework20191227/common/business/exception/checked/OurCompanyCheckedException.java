package ru.epam.javacore.homework20191227.common.business.exception.checked;

public class OurCompanyCheckedException extends Exception {

    public OurCompanyCheckedException(String message){
        super(message);
    }

    public OurCompanyCheckedException(String message, Exception cause){
        super(message);
        this.initCause(cause);
    }

}
