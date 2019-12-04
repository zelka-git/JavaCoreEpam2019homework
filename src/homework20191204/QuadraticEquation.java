package homework20191204;

public class QuadraticEquation {
    private float oldCoefficient;
    private float averageCoefficient;
    private float lowCoefficient;
    private Float rootOne;
    private Float rootTwo;

    public QuadraticEquation(float oldCoefficient, float averageCoefficient, float lowCoefficient) {
        this.oldCoefficient = oldCoefficient;
        this.averageCoefficient = averageCoefficient;
        this.lowCoefficient = lowCoefficient;
        resolveAcq(oldCoefficient, averageCoefficient, lowCoefficient);
    }

    private void resolveAcq(float a, float b, float c) {
        float discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
        } else if (discriminant == 0) {
            this.rootOne = this.rootTwo = -b / (2 * a);
        } else {
            this.rootOne = (float) ((-b + Math.sqrt(discriminant)) / (2 * a));
            this.rootTwo = (float) ((-b - Math.sqrt(discriminant)) / (2 * a));
        }
    }

    public Float getRootOne() {
        return rootOne;
    }

    public Float getRootTwo() {
        return rootTwo;
    }

    @Override
    public String toString() {
        return "QuadraticEquation{" +
                +oldCoefficient + "x^2" + (averageCoefficient > 0 ? "+" : "") +
                +averageCoefficient + "x" + (lowCoefficient > 0 ? "+" : "") +
                +lowCoefficient + "=0" +
                ", x1=" + rootOne +
                ", x2=" + rootTwo +
                '}';
    }

    public static void main(String[] args) {
        QuadraticEquation quadraticEquation1 = new QuadraticEquation(1, -3, -4);
        System.out.println(quadraticEquation1);
        System.out.println("root one: " + quadraticEquation1.getRootOne());
        System.out.println("root two: " + quadraticEquation1.getRootTwo());
        QuadraticEquation quadraticEquation2 = new QuadraticEquation(1, 2, 1);
        System.out.println(quadraticEquation2);
        System.out.println("root one: " + quadraticEquation2.getRootOne());
        System.out.println("root two: " + quadraticEquation2.getRootTwo());
        QuadraticEquation quadraticEquation3 = new QuadraticEquation(1, 2, 3);
        System.out.println(quadraticEquation3);
        System.out.println("root one: " + quadraticEquation3.getRootOne());
        System.out.println("root two: " + quadraticEquation3.getRootTwo());
    }

}
