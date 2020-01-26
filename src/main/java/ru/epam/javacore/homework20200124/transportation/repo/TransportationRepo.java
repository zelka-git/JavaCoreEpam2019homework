package ru.epam.javacore.homework20200124.transportation.repo;

import ru.epam.javacore.homework20200124.common.business.repo.CommonRepo;
import ru.epam.javacore.homework20200124.transportation.domain.Transportation;

import java.io.Serializable;

public interface TransportationRepo extends CommonRepo<Transportation, Long>, Serializable {

}

