package ru.epam.javacore.homework20191220.carrier.service;

import main.homework20191220.carrier.domain.Carrier;
import main.homework20191220.common.service.CommonService;

public interface CarrierService extends CommonService {
    void add(Carrier carrier);

    Carrier[] getAll();

    Carrier[] getByName(String name);

    Carrier getById(Long id);

    boolean update(Carrier carrier);
}
