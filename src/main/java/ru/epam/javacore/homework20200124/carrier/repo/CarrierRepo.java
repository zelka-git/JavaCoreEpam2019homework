package ru.epam.javacore.homework20200124.carrier.repo;

import ru.epam.javacore.homework20200124.carrier.domain.Carrier;
import ru.epam.javacore.homework20200124.common.business.repo.CommonRepo;

import java.io.Serializable;
import java.util.List;

public interface CarrierRepo extends CommonRepo<Carrier, Long>, Serializable {

    List<Carrier> getByName(String name);

}
