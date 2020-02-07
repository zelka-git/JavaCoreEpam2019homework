package ru.epam.javacore.homework20200205.common.business.exception.checked;

import ru.epam.javacore.homework20200205.common.business.exception.checked.OurCompanyCheckedException;

public class InitStorageException extends OurCompanyCheckedException {

    public InitStorageException(String message){
        super(message);
    }

}
