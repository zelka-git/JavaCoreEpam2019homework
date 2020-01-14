package main.homework20191223.carrier.service;

import main.homework20191223.carrier.domain.Carrier;
import main.homework20191223.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier> {

    List<Carrier> getByName(String name);

}
