package ru.epam.javacore.homework20200113.carrier.service;

import main.homework20200113.carrier.domain.Carrier;
import main.homework20200113.common.business.service.CommonService;

import java.io.Serializable;
import java.util.List;

public interface CarrierService extends CommonService<Carrier, Long>, Serializable {

    List<Carrier> getByName(String name);

}
