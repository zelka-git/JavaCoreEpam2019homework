package homework20191211;

import homework20191211.cargo.domain.Cargo;
import homework20191211.carrier.domain.Carrier;
import homework20191211.transportation.Transportation;

public class Storage {
    private static Cargo[] cargo;
    private static Carrier[] carriers;
    private static Transportation[] transportation;

    private static int sizeCargo;
    private static int sizeCarrier;
    private static int sizeTransportation;

    public static void addCargo(Cargo cargo){
        if(Storage.cargo == null){
            Storage.cargo = new Cargo[10];
        }else if(sizeCargo == Storage.cargo.length) {
            Cargo[] newArrayCargo = new Cargo[(int) (sizeCargo * 1.5)];
            System.arraycopy(Storage.cargo, 0, newArrayCargo, 0, sizeCargo);
            Storage.cargo = newArrayCargo;
        }
        Storage.cargo[sizeCargo] = cargo;
        sizeCargo++;
    }

    public static void addCarrier(Carrier carrier){
        if(Storage.carriers == null){
            Storage.carriers = new Carrier[10];
        }else if(sizeCarrier == Storage.carriers.length){
            Carrier[] newArrayCarrier = new Carrier[(int) (sizeCarrier*1.5)];
            System.arraycopy(Storage.carriers, 0, newArrayCarrier, 0, sizeCarrier);
            Storage.carriers = newArrayCarrier;
        }
        Storage.carriers[sizeCarrier] = carrier;
        sizeCarrier++;

    }

    public static void addTransportation(Transportation transportation){
        if (Storage.transportation == null){
            Storage.transportation = new Transportation[10];
        }else if(sizeTransportation == Storage.transportation.length){
            Transportation[] newArrayTrans = new Transportation[(int) (sizeTransportation * 1.5)];
            System.arraycopy(Storage.transportation, 0, newArrayTrans, 0, sizeTransportation);
            Storage.transportation = newArrayTrans;
        }
        Storage.transportation[sizeTransportation] = transportation;
        sizeTransportation++;
    }

    public static void printAllCargo(){
        for (int i = 0; i < sizeCargo; i++) {
            System.out.println(cargo[i]);
        }
    }

    public static void printAllCarrier(){
        for (int i = 0; i < sizeCarrier; i++) {
            System.out.println(carriers[i]);
        }
    }

    public static void printAllTransportation(){
        for (int i = 0; i < sizeTransportation; i++) {
            System.out.println(transportation[i]);
        }
    }

    public static Cargo getCargoById(long id){
        for (Cargo item: cargo) {
            if(item != null){
                if(item.getId().equals(id)){
                    return item;
                }
            }
        }
        return null;
    }

    public static Cargo getCargoByName(String name){
        for (Cargo item: cargo){
            if(item != null){
                if(item.getName().equals(name)){
                    return  item;
                }
            }
        }
        return null;
    }

    public static Carrier getCarrierById(long id){
        for (Carrier item: carriers) {
            if(item != null){
                if(item.getId().equals(id)){
                    return item;
                }
            }
        }
        return null;
    }

    public static Carrier getCarrierByName(String name){
        for (Carrier item: carriers) {
            if(item != null)
            {
                if(item.getName().equals(name)){
                    return item;
                }
            }
        }
        return null;
    }

    public static Transportation getTransportationById(long id){
        for (Transportation item: transportation) {
            if(item != null){
                if (item.getId().equals(id)){
                    return item;
                }
            }
        }
        return null;
    }

    public static Cargo[] getAllCargo(){
        if(sizeCargo < cargo.length){
            Cargo[] result = new Cargo[sizeCargo];
            System.arraycopy(cargo, 0, result, 0, sizeCargo);
            return result;
        }
        return cargo;
    }

    public static Carrier[] getAllCarrier(){
        if(sizeCarrier < carriers.length){
            Carrier[] result = new Carrier[sizeCarrier];
            System.arraycopy(carriers, 0, result, 0, sizeCarrier);
            return result;
        }
        return carriers;
    }

    public static Transportation[] getAllTransportation(){
        if(sizeTransportation < transportation.length){
            Transportation[] result = new Transportation[sizeTransportation];
            System.arraycopy(transportation, 0, result, 0 , sizeTransportation);
            return result;
        }
        return transportation;
    }


}
