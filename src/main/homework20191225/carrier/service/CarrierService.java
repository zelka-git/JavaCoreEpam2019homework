package main.homework20191225.carrier.service;

import main.homework20191225.carrier.domain.Carrier;
import main.homework20191225.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier, Long> {

    List<Carrier> getByName(String name);

}
