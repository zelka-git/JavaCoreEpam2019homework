package ru.epam.javacore.homework20191220.transportation.repo;

import main.homework20191220.common.repo.CommonRepo;
import main.homework20191220.transportation.domain.Transportation;

public interface TransportationRepo extends CommonRepo {
    void add(Transportation transportation);

    Transportation[] getAll();

    Transportation getById(long id);

    boolean update(Transportation transportation);

}

