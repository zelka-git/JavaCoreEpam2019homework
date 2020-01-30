package ru.epam.javacore.homework20200129.common.business.exception.unchecked;

public class OurCompanyUncheckedException extends RuntimeException {

    public OurCompanyUncheckedException(String message) {
        super(message);
    }
}
