package main.homework20191227.cargo.exception.unckecked;

import main.homework20191227.common.business.exception.unchecked.OurCompanyUncheckedException;

public class CargoDeleteConstraintViolationException extends OurCompanyUncheckedException {

    private static final String MESSAGE = "Can't delete cargo with id '%s'. There are transportations which relates to it!";

    public CargoDeleteConstraintViolationException(String message){
        super(message);
    }

    public CargoDeleteConstraintViolationException(long cargoId){
        this(String.format(MESSAGE, cargoId));
    }
}
