package ru.epam.javacore.homework20200207.cargo.exception.unckecked;

import ru.epam.javacore.homework20200207.common.business.exception.unchecked.OurCompanyUncheckedException;

public class CargoDeleteConstraintViolationException extends OurCompanyUncheckedException {

    private static final String MESSAGE = "Can't delete cargo with id '%s'. There are transportations which relates to it!";

    public CargoDeleteConstraintViolationException(String message){
        super(message);
    }

    public CargoDeleteConstraintViolationException(long cargoId){
        this(String.format(MESSAGE, cargoId));
    }
}
