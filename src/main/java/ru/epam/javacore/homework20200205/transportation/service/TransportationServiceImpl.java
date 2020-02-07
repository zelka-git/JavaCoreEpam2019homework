package ru.epam.javacore.homework20200205.transportation.service;

import ru.epam.javacore.homework20200205.common.solutions.utils.ArrayUtils;
import ru.epam.javacore.homework20200205.transportation.domain.Transportation;
import ru.epam.javacore.homework20200205.transportation.repo.TransportationRepo;
import ru.epam.javacore.homework20200205.transportation.service.TransportationService;

import java.util.List;
import java.util.Optional;

public class TransportationServiceImpl implements TransportationService {

    private TransportationRepo transportationRepo;

    public TransportationServiceImpl(TransportationRepo transportationRepo) {
        this.transportationRepo = transportationRepo;
    }

    @Override
    public void add(Transportation transportation) {
//        System.out.println("Begin to add transportation!");
        if (transportation.getCargo() == null) {
            System.out.println("transportation must have a cargo!");
        } else if (transportation.getCarrier() == null) {
            System.out.println("transportation must have a carrier!");
        } else if (transportation.getDescription() == null) {
            System.out.println("transportation must have a description!");
        } else if (transportation.getBillTo() == null) {
            System.out.println("transportation must have a BillTo!");
        } else if (transportation.getDate() == null) {
            System.out.println("transportation must have a date!");
        } else {
            transportationRepo.add(transportation);
//            System.out.println("transportation added!!!");
        }
    }

    @Override
    public List<Transportation> getAll() {
        return transportationRepo.getAll();
    }

    @Override
    public Optional<Transportation> getById(Long id) {
        if (id != null) {
            transportationRepo.getById(id);
        }
        return null;
    }

    @Override
    public boolean update(Transportation transportation) {
        if (transportation == null) {
            System.out.println("value NULL don't available!");
        } else if (transportation.getId() == null) {
            System.out.println("transportation must have a ID!");
        } else if (transportation.getCargo() == null) {
            System.out.println("transportation must have a cargo!");
        } else if (transportation.getCarrier() == null) {
            System.out.println("transportation must have a carrier!");
        } else if (transportation.getDescription() == null) {
            System.out.println("transportation must have a description!");
        } else if (transportation.getBillTo() == null) {
            System.out.println("transportation must have a BillTo!");
        } else if (transportation.getDate() == null) {
            System.out.println("transportation must have a date!");
        } else if (transportationRepo.update(transportation)) {
            System.out.println("transportation added!!!");
            return true;
        }
        System.out.println("transportation don't found");
        return false;
    }


    @Override
    public boolean deleteById(Long id) {
        return transportationRepo.deleteById(id);
    }

    @Override
    public void printAll() {
        ArrayUtils.printArray(transportationRepo.getAll());
    }
}
