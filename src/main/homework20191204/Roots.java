package main.homework20191204;

public class Roots {
    private Float rootOne;
    private Float rootTwo;

    public Float getRootOne() {
        return rootOne;
    }

    public Float getRootTwo() {
        return rootTwo;
    }

    public void calculatingRoots(float a, float b, float c, float d){
        this.rootOne = (float) ((-b + Math.sqrt(d)) / (2 * a));
        this.rootTwo = (float) ((-b - Math.sqrt(d)) / (2 * a));
    }


}
