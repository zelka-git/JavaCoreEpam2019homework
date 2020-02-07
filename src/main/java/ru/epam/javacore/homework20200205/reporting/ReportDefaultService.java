package ru.epam.javacore.homework20200205.reporting;

import ru.epam.javacore.homework20200205.cargo.domain.*;
import ru.epam.javacore.homework20200205.cargo.service.CargoService;
import ru.epam.javacore.homework20200205.carrier.domain.Carrier;
import ru.epam.javacore.homework20200205.carrier.service.CarrierService;
import ru.epam.javacore.homework20200205.common.business.exception.checked.ReportException;
import ru.epam.javacore.homework20200205.common.solutions.utils.DataUtils;
import ru.epam.javacore.homework20200205.reporting.ReportService;
import ru.epam.javacore.homework20200205.transportation.domain.Transportation;
import ru.epam.javacore.homework20200205.transportation.service.TransportationService;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ReportDefaultService implements ReportService {

    private static final String sep = " | ";
    private static final String FILE_PATH = "./src/main/resources/print_out2.txt";

    private final CargoService cargoService;
    private final CarrierService carrierService;
    private final TransportationService transportationService;

    public ReportDefaultService(
            CargoService cargoService,
            CarrierService carrierService,
            TransportationService transportationService) {
        this.cargoService = cargoService;
        this.carrierService = carrierService;
        this.transportationService = transportationService;
    }

    @Override
    public void exportData() throws ReportException {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(new File(FILE_PATH))
        )) {
            List<Cargo> cargos = cargoService.getAll();
            List<Carrier> carriers = carrierService.getAll();
            List<Transportation> transportations = transportationService.getAll();

            List<String> cargosString = convertCargosToStringList(cargos);
            List<String> carriersString = canvertCarriersToStringList(carriers);
            List<String> transportationsString = convertTransportationsToStringList(transportations);
            writeToFile(cargosString, carriersString, transportationsString, writer);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ReportException(e.getMessage());
        }
    }
    private List<String> convertCargosToStringList(List<Cargo> cargos) {
        List<String> list = new ArrayList<>();
        for (Cargo cargo : cargos) {
            list.add(convertCargoToString(cargo));
        }
        return list;
    }

    private String convertCargoToString(Cargo cargo) {

        StringBuilder builder = new StringBuilder();
        builder.append(cargo.getId() + sep);
        builder.append(cargo.getName() + sep);
        builder.append(cargo.getWeight() + sep);
        CargoType cargoType = cargo.getCargoType();
        builder.append(cargoType + sep);
        switch (cargoType) {
            case FOOD:
                builder.append("\t-\t" + sep);
                builder.append("\t-\t" + sep);
                builder.append("\t-\t" + sep);
                builder.append(DataUtils.formatter.format(((FoodCargo) cargo).getExpirationDate()) + sep);
                builder.append(((FoodCargo) cargo).getStoreTemperature());
                break;
            case CLOTHES:
                builder.append(((ClothesCargo) cargo).getSize() + sep);
                builder.append(((ClothesCargo) cargo).getMaterial());
                builder.append("\t-\t" + sep);
                builder.append("\t-\t" + sep);
                builder.append("\t-\t");
                break;
            case COMPUTERS:
                builder.append("\t-\t" + sep);
                builder.append("\t-\t" + sep);
                builder.append(((ComputerCargo) cargo).getDescription() + sep);
                builder.append("\t-\t" + sep);
                builder.append("\t-\t");

        }
        return builder.toString();
    }

    private List<String> canvertCarriersToStringList(List<Carrier> carriers) {
        List<String> list = new ArrayList<>();
        for (Carrier carrier : carriers) {
            list.add(convertCarrierToString(carrier));
        }
        return list;
    }

    private String convertCarrierToString(Carrier carrier) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(carrier.getId() + sep);
        stringBuilder.append(carrier.getName() + sep);
        stringBuilder.append(carrier.getAddress() + sep);
        stringBuilder.append(carrier.getCarrierType().name() + sep);
        return stringBuilder.toString();
    }

    private List<String> convertTransportationsToStringList(List<Transportation> transportations) {
        List<String> list = new ArrayList<>();
        for (Transportation transportation : transportations) {
            list.add(convertTransportationToString(transportation));
        }
        return list;
    }

    private String convertTransportationToString(Transportation transportation) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(transportation.getId() + sep);
        stringBuilder.append(transportation.getCargo().getId() + sep);
        stringBuilder.append(transportation.getCarrier().getId() + sep);
        stringBuilder.append(transportation.getDescription() + sep);
        stringBuilder.append(transportation.getBillTo() + sep);
        stringBuilder.append(DataUtils.formatter.format(transportation.getDate() )+ sep);
        return stringBuilder.toString();
    }

    private void writeToFile(List<String> cargosString, List<String> carriersString, List<String> transportationsString, PrintWriter writer) {
        writer.write("---CARGOS---" + "\n");
        for (String cargo : cargosString) {
            writer.write(cargo + "\n");
        }
        writer.write("\n" + "---CARRIERS---" + "\n");
        for (String carrier : carriersString) {
            writer.write(carrier + "\n");
        }
        writer.write("\n" + "---TRANSPORTATIONS---" + "\n");
        for (String trans : transportationsString) {
            writer.write(trans + "\n");
        }
    }

}
