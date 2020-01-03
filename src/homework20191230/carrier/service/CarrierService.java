package homework20191230.carrier.service;

import homework20191230.carrier.domain.Carrier;
import homework20191230.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier, Long> {

    List<Carrier> getByName(String name);

}
