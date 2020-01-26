package ru.epam.javacore.homework20200124.storage;

import ru.epam.javacore.homework20200124.cargo.domain.Cargo;
import ru.epam.javacore.homework20200124.carrier.domain.Carrier;
import ru.epam.javacore.homework20200124.transportation.domain.Transportation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Storage implements Serializable {

    private static final int ARRAY_CAPACITY = 10;

    public static Cargo[] cargoArray = new Cargo[ARRAY_CAPACITY];
    public static Carrier[] carriersArray = new Carrier[ARRAY_CAPACITY];
    public static Transportation[] transportationArray = new Transportation[ARRAY_CAPACITY];

    public static int sizeCargo;
    public static int sizeCarrier;
    public static int sizeTransportation;

    public static List<Cargo> cargoList = new ArrayList<>();
    public static List<Carrier> carriersList = new ArrayList<>();
    public static List<Transportation> transportationList = new ArrayList<>();


}
