package homework20191218.transportation.service;

import homework20191218.transportation.domain.Transportation;

public interface TransportationService {
    void add(Transportation transportation);

    Transportation[] getAll();

    Transportation getById(Long id);

    Transportation remove(Long id);

    boolean update(Transportation transportation);
}
