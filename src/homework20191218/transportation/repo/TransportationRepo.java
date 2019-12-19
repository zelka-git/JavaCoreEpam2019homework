package homework20191218.transportation.repo;

import homework20191218.common.repo.CommonRepo;
import homework20191218.transportation.domain.Transportation;

public interface TransportationRepo extends CommonRepo {
    void add(Transportation transportation);

    Transportation[] getAll();

    Transportation getById(long id);

    boolean update(Transportation transportation);

}

