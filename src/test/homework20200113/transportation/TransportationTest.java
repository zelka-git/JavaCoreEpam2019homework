package test.homework20200113.transportation;

import main.homework20200113.cargo.domain.CargoType;
import main.homework20200113.cargo.domain.ClothesCargo;
import main.homework20200113.carrier.domain.Carrier;
import main.homework20200113.carrier.domain.CarrierType;
import main.homework20200113.common.solutions.utils.FileUtils;
import main.homework20200113.transportation.domain.Transportation;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TransportationTest {
    @Test
    public void testCarrierSerialize() throws Exception {

        Path file = null;
        try {
            file = Files.createTempFile("testCargo", ".txt");

            String nameCargo = "dress";
            int weightCargo = 123;
            long idCargo = 99L;
            String materialCargo = "cotton";
            String sizeCargo = "M";

            String nameCarrier = "delivery";
            String addressCarrier = "Perm";
            long idCarrier = 199L;
            CarrierType carrierType = CarrierType.CAR;

            String descriptionTransportation = "this is transportation";
            String billToTransportation = "I. D. Titov";
            Date dateTransportation = new Date();

            try (ObjectOutput objectOutput = new ObjectOutputStream(
                    new FileOutputStream(file.toFile()))) {
                ClothesCargo cargo = new ClothesCargo();
                cargo.setName(nameCargo);
                cargo.setWeight(weightCargo);
                cargo.setId(idCargo);
                cargo.setMaterial(materialCargo);
                cargo.setSize(sizeCargo);

                Carrier carrier = new Carrier();
                carrier.setName(nameCarrier);
                carrier.setAddress(addressCarrier);
                carrier.setId(idCarrier);
                carrier.setCarrierType(carrierType);

                Transportation transportation = new Transportation();
                transportation.setCargo(cargo);
                transportation.setCarrier(carrier);
                transportation.setBillTo(billToTransportation);
                transportation.setDescription(descriptionTransportation);
                transportation.setDate(dateTransportation);

                objectOutput.writeObject(transportation);
            }

//            Transportation transportationRead = readCarrierFromFile(file.toFile().getAbsolutePath());
            Transportation transportationRead = FileUtils.readObjectFromFile(file.toFile().getAbsolutePath());

            assertEquals(nameCargo, transportationRead.getCargo().getName());
            assertEquals(weightCargo, transportationRead.getCargo().getWeight());
            assertEquals(CargoType.CLOTHES, transportationRead.getCargo().getCargoType());
            assertEquals(materialCargo, ((ClothesCargo)transportationRead.getCargo()).getMaterial());
            assertEquals(sizeCargo, ((ClothesCargo)transportationRead.getCargo()).getSize());
            assertEquals(idCargo, transportationRead.getCargo().getId());

            assertEquals(nameCarrier, transportationRead.getCarrier().getName());
            assertEquals(addressCarrier, transportationRead.getCarrier().getAddress());
            assertEquals(carrierType, transportationRead.getCarrier().getCarrierType());
            assertEquals(idCarrier, transportationRead.getCarrier().getId());

            assertEquals(descriptionTransportation, transportationRead.getDescription());
            assertEquals(billToTransportation, transportationRead.getBillTo());
            assertEquals(dateTransportation, transportationRead.getDate());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    Files.delete(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}