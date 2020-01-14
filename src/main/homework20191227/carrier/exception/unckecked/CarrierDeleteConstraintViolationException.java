package main.homework20191227.carrier.exception.unckecked;

import main.homework20191227.common.business.exception.unchecked.OurCompanyUncheckedException;

public class CarrierDeleteConstraintViolationException extends OurCompanyUncheckedException {

    private static final String MESSAGE = "Cant delete carrier with id '%s'. There are transportations which relates to it!";

    public CarrierDeleteConstraintViolationException(String message) {
        super(message);
    }

    public CarrierDeleteConstraintViolationException(long carrierId) {
        this(String.format(MESSAGE, carrierId));
    }
}
