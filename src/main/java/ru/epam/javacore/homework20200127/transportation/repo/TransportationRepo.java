package ru.epam.javacore.homework20200127.transportation.repo;

import ru.epam.javacore.homework20200127.common.business.repo.CommonRepo;
import ru.epam.javacore.homework20200127.transportation.domain.Transportation;

import java.io.Serializable;

public interface TransportationRepo extends CommonRepo<Transportation, Long>, Serializable {

}

