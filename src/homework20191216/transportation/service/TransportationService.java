package homework20191216.transportation.service;

import homework20191216.transportation.domain.Transportation;
import homework20191216.transportation.repo.TransportationRepo;

public interface TransportationService {
    void add(Transportation transportation, TransportationRepo typeStorage);

    Transportation[] getAll(TransportationRepo typeStorage);

    Transportation getById(Long id, TransportationRepo typeStorage);

    Transportation remove(Long id, TransportationRepo typeStorage);
}
