package homework20191220.carrier.repo;

import homework20191220.carrier.domain.Carrier;
import homework20191220.common.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo {
    void add(Carrier carrier);

    Carrier[] getAll();

    Carrier[] getByName(String name);

    Carrier getById(long id);

    boolean update(Carrier carrier);

}
