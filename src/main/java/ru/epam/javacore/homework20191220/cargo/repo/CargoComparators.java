package ru.epam.javacore.homework20191220.cargo.repo;

import main.homework20191220.cargo.domain.Cargo;

import java.util.Comparator;

public class CargoComparators {
    private CargoComparators(){}
    public static final Comparator<Cargo> COMPARE_BY_NAME = new Comparator<Cargo>(){
        @Override
        public int compare(Cargo o1, Cargo o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public static final Comparator<Cargo> COMPARE_BY_WEIGHT = new Comparator<Cargo>() {
        @Override
        public int compare(Cargo o1, Cargo o2) {
            return Integer.compare(o1.getWeight(), o2.getWeight());
        }
    };

    public static final Comparator<Cargo> COMPARE_BY_NAME_WEIGHT = new Comparator<Cargo>(){

        @Override
        public int compare(Cargo o1, Cargo o2) {
            if (!o1.getName().equals(o2.getName())) {
                return o1.getName().compareTo(o2.getName());
            }
            return Integer.compare(o1.getWeight(), o2.getWeight());
        }
    };

    public static final Comparator<Cargo> COMPARE_BY_WEIGHT_NAME = new Comparator<Cargo>(){

        @Override
        public int compare(Cargo o1, Cargo o2) {
            if (Integer.compare(o1.getWeight(), o2.getWeight()) != 0) {
                return Integer.compare(o1.getWeight(), o2.getWeight());
            }
            return o1.getName().compareTo(o2.getName());
        }
    };
}
