package homework20191223.carrier.repo;

import homework20191223.carrier.domain.Carrier;
import homework20191223.common.business.repo.CommonRepo;

import java.util.List;

public interface CarrierRepo extends CommonRepo<Carrier> {

    List<Carrier> getByName(String name);

}
