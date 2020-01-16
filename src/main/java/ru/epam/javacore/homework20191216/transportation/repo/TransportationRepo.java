package ru.epam.javacore.homework20191216.transportation.repo;

import ru.epam.javacore.homework20191216.common.repo.CommonRepo;
import ru.epam.javacore.homework20191216.transportation.domain.Transportation;

public interface TransportationRepo extends CommonRepo {
    void add(Transportation transportation);

    Transportation[] getAll();

    Transportation getById(long id);

}

