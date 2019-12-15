package homework20191213.transportation.service;

import homework20191213.transportation.domain.Transportation;

public interface TransportationService {
    void add(Transportation transportation);

    Transportation[] getAll();

    Transportation getById(long id);

    Transportation remove(long id);
}
