package ru.epam.javacore.homework20191218.carrier.service;

import ru.epam.javacore.homework20191218.carrier.domain.Carrier;
import ru.epam.javacore.homework20191218.common.service.CommonService;

public interface CarrierService extends CommonService {
    void add(Carrier carrier);

    Carrier[] getAll();

    Carrier[] getByName(String name);

    Carrier getById(Long id);

    boolean update(Carrier carrier);
}
