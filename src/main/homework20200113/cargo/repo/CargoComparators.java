package main.homework20200113.cargo.repo;

import main.homework20200113.cargo.domain.Cargo;

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

}
