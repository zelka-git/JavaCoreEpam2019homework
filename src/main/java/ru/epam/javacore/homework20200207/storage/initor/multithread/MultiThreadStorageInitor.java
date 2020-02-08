package ru.epam.javacore.homework20200207.storage.initor.multithread;

import ru.epam.javacore.homework20200207.cargo.domain.Cargo;
import ru.epam.javacore.homework20200207.carrier.domain.Carrier;
import ru.epam.javacore.homework20200207.common.business.exception.checked.InitStorageException;
import ru.epam.javacore.homework20200207.storage.initor.fileinitor.BaseFileInitor;
import ru.epam.javacore.homework20200207.storage.initor.multithread.CargoParser;
import ru.epam.javacore.homework20200207.storage.initor.multithread.CarrierParser;
import ru.epam.javacore.homework20200207.storage.initor.multithread.TransportationParser;
import ru.epam.javacore.homework20200207.transportation.domain.Transportation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MultiThreadStorageInitor extends BaseFileInitor {

    private final String path;

    public MultiThreadStorageInitor(String path) {
        this.path = path;
    }

    @Override
    public void initStorage() throws InitStorageException {
        try {
//            CargoParser cargoParser = new CargoParser(path);
//            CarrierParser carrierParser = new CarrierParser(path);
//            TransportationParser transportationParser = new TransportationParser(path);
//
//            List<Thread> parsers = Arrays.asList(
//                    new Thread(cargoParser),
//                    new Thread(carrierParser),
//                    new Thread(transportationParser)
//            );


            ru.epam.javacore.homework20200207.storage.initor.multithread.CargoParser cargoParser = new CargoParser(path);
            ru.epam.javacore.homework20200207.storage.initor.multithread.CarrierParser carrierParser = new CarrierParser(path);
            ru.epam.javacore.homework20200207.storage.initor.multithread.TransportationParser transportationParser = new TransportationParser(path);

            List<Thread> parsers = Arrays.asList(
                    new Thread(cargoParser::run),
                    new Thread(carrierParser::run),
                    new Thread(transportationParser::run)
            );

            startParseWithThreads(parsers);
            waitAllParserHaveFinished(parsers);

            boolean hasErrorWhileParseFile =
                    cargoParser.isHasError() && carrierParser.isHasError() && transportationParser
                            .isHasError();

            if (hasErrorWhileParseFile) {
                throw new Exception("Error while parse data!");
            }

            Map<String, Cargo> cargoMap = cargoParser.getCargoMap();
            Map<String, Carrier> carrierMap = carrierParser.getCarrierMap();
            List<ParsedTransportation> transportations = transportationParser.getTransportations();
            setReferencesBetweenEntities(cargoMap, carrierMap, transportations);

            persistCargos(cargoMap.values());
            persistCarriers(carrierMap.values());
            List<Transportation> transportationList = getTransportationsFromParsedObject(transportations);
            persistTransportations(transportationList);


        } catch (Exception e) {
            throw new InitStorageException(e.getMessage());
        }
    }

    private void startParseWithThreads(List<Thread> threads) {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    private void waitAllParserHaveFinished(List<Thread> threads) throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
