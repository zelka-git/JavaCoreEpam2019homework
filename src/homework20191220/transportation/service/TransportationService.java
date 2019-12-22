package homework20191220.transportation.service;

import homework20191220.common.service.CommonService;
import homework20191220.transportation.domain.Transportation;

public interface TransportationService extends CommonService {
    void add(Transportation transportation);

    Transportation[] getAll();

    Transportation getById(Long id);

    boolean update(Transportation transportation);

}
