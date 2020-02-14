package ru.epam.javacore.homework20200210.cargo.domain;

import ru.epam.javacore.homework20200210.transportation.domain.Transportation;

import java.util.List;

public class ProxyCargo extends Cargo {

    private static final String ERROR_MSG = "Unsupported operation for proxy!";

    public ProxyCargo() {
    }

    public ProxyCargo(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException(ERROR_MSG);
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException(ERROR_MSG);
    }

    @Override
    public int getWeight() {
        throw new UnsupportedOperationException(ERROR_MSG);
    }

    @Override
    public void setWeight(int weight) {
        throw new UnsupportedOperationException(ERROR_MSG);
    }

    @Override
    public void setCargoType(CargoType cargoType) {
        super.setCargoType(cargoType);
    }

    @Override
    public List<Transportation> getTransportations() {
        throw new UnsupportedOperationException(ERROR_MSG);
    }

    @Override
    public void setTransportations(List<Transportation> transportations) {
        throw new UnsupportedOperationException(ERROR_MSG);
    }

    @Override
    public String toString() {
        return id + "";
    }

    @Override
    public CargoType getCargoType() {
        return null;
    }
}
