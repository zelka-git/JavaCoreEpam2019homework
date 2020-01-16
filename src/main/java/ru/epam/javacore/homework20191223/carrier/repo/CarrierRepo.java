package ru.epam.javacore.homework20191223.carrier.repo;

import ru.epam.javacore.homework20191223.carrier.domain.Carrier;
import ru.epam.javacore.homework20191223.common.business.repo.CommonRepo;

import java.util.List;

public interface CarrierRepo extends CommonRepo<Carrier> {

    List<Carrier> getByName(String name);

}
