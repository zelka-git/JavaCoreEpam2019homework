package homework20191202;

public class Task {

    public static void resolveAcq(float a, float b, float c) {
        float x1;
        float x2;
        float discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            System.out.println("no roots");
        } else if (discriminant == 0) {
            x1 = x2 = -b / 2 * a;
            System.out.println("x1 = x2 = " + x1);
        } else {
            x1 = (float) ((-b + Math.sqrt(discriminant)) / 2 * a);
            x2 = (float) ((-b - Math.sqrt(discriminant)) / 2 * a);
            System.out.println("x1 = " + x1 + "\nx2 = " + x2);
        }
    }

    public static void main(String[] args) {
        resolveAcq(1, 2, 3);
        resolveAcq(1, 2, 1);
        resolveAcq(1, -3, -4);

    }
}
