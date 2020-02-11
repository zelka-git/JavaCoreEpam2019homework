package ru.epam.javacore.homework20200210.common.business.exception.unchecked;

public class SqlException extends OurCompanyUncheckedException{

    public SqlException(Exception cause) {
        super(cause.getMessage());
    }

    public SqlException(String message) {
        super(message);
    }

}