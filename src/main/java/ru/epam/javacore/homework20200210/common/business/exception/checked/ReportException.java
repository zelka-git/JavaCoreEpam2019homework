package ru.epam.javacore.homework20200210.common.business.exception.checked;

import ru.epam.javacore.homework20200210.common.business.exception.checked.OurCompanyCheckedException;

public class ReportException extends OurCompanyCheckedException {

    public ReportException(String message) {
        super(message);
    }
}

