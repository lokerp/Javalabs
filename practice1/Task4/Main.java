package Task4;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        final int APARTSPERFLOOR = 3;
        System.out.print("Введите количество этажей: ");
        int floorsCount = getNaturalInput();
        System.out.print("Введите квартиру: ");
        int apartID = getNaturalInput();

        int floor = (Math.ceilDiv(apartID, APARTSPERFLOOR));
        if (floor % 2 == 0)
            floor += 1;

        System.out.print("Лифт привезёт пассажира на " + floor + " этаж");
    }

    public static int getNaturalInput() {
        int input = 0;
        try {
            input = in.nextInt();
            if (input <= 0) {
                System.out.println("\nОшибка ввода!");
                System.exit(-1);
            }
        }
        catch (Exception e) {
            System.out.println("\nОшибка ввода!");
            System.exit(-1);
        }

        return input;
    }
}
