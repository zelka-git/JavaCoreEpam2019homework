package ru.epam.javacore.homework20191204;

public class QuadraticEquation {
    private float a;
    private float b;
    private float c;
    private Roots roots;

    public QuadraticEquation(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private void resolveAcq(float a, float b, float c) {
        float discriminant = b * b - 4 * a * c;
        if (discriminant >= 0) {
            roots.calculatingRoots(a, b, c, discriminant);
        }
    }

    public void resolveAcq() {
        roots = new Roots();
        resolveAcq(a, b, c);
    }

    public Float getRootOne() {
        return roots.getRootOne();
    }

    public Float getRootTwo() {
        return roots.getRootTwo();
    }

    @Override
    public String toString() {
        return "QuadraticEquation{" +
                +a + "x^2" + (b > 0 ? "+" : "") +
                +b + "x" + (c > 0 ? "+" : "") +
                +c + "=0" +
                ", x1=" + roots.getRootOne() +
                ", x2=" + roots.getRootTwo() +
                '}';
    }

    public static void main(String[] args) {
        QuadraticEquation quadraticEquation1 = new QuadraticEquation(1, -3, -4);
        quadraticEquation1.resolveAcq();
        System.out.println(quadraticEquation1);
        System.out.println("root one: " + quadraticEquation1.getRootOne());
        System.out.println("root two: " + quadraticEquation1.getRootTwo());
        QuadraticEquation quadraticEquation2 = new QuadraticEquation(1, 2, 1);
        quadraticEquation2.resolveAcq();
        System.out.println(quadraticEquation2);
        System.out.println("root one: " + quadraticEquation2.getRootOne());
        System.out.println("root two: " + quadraticEquation2.getRootTwo());
        QuadraticEquation quadraticEquation3 = new QuadraticEquation(1, 2, 3);
        quadraticEquation3.resolveAcq();
        System.out.println(quadraticEquation3);
        System.out.println("root one: " + quadraticEquation3.getRootOne());
        System.out.println("root two: " + quadraticEquation3.getRootTwo());
    }

}
