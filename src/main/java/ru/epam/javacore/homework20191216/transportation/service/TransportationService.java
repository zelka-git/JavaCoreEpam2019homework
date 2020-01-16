package ru.epam.javacore.homework20191216.transportation.service;

import main.homework20191216.transportation.domain.Transportation;

public interface TransportationService {
    void add(Transportation transportation);

    Transportation[] getAll();

    Transportation getById(Long id);

    Transportation remove(Long id);
}
