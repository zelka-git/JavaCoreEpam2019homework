package main.homework20191227.carrier.repo;

import main.homework20191227.carrier.domain.Carrier;
import main.homework20191227.common.business.repo.CommonRepo;

import java.util.List;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

    List<Carrier> getByName(String name);

}
