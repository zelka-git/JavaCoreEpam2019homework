package ru.epam.javacore.homework20191216.carrier.service;

import ru.epam.javacore.homework20191216.carrier.domain.Carrier;

public interface CarrierService {
    void add(Carrier carrier);

    Carrier[] getAll();

    Carrier[] getByName(String name);

    Carrier getById(Long id);

    Carrier remove(Long id);
}
