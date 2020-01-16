package ru.epam.javacore.homework20191218.carrier.repo;

import main.homework20191218.carrier.domain.Carrier;
import main.homework20191218.common.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo {
    void add(Carrier carrier);

    Carrier[] getAll();

    Carrier[] getByName(String name);

    Carrier getById(long id);

    boolean update(Carrier carrier);

}