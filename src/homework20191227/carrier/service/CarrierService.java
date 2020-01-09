package homework20191227.carrier.service;

import homework20191227.carrier.domain.Carrier;
import homework20191227.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier, Long> {

    List<Carrier> getByName(String name);

}