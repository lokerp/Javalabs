package Task1;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final var NAMES = new String[] {
                "Крыса",
                "Корова",
                "Тигр",
                "Заяц",
                "Дракон",
                "Змея",
                "Лошадь",
                "Овца",
                "Обезьяна",
                "Курица",
                "Собака",
                "Свинья"
        };
        final var CYCLESTART = 1996;

        Scanner in = new Scanner(System.in);
        int year = 0;
        System.out.print("Введите год: ");
        try {
            year = in.nextInt();
        }
        catch (Exception e) {
            System.out.println("\nОшибка ввода!");
            System.exit(-1);
        }
        in.close();
        int mod = (year - CYCLESTART) % NAMES.length;
        if (mod < 0)
            mod += NAMES.length;
        String yearName = NAMES[mod];
        System.out.println("Символ вашего года: " + yearName);
    }
}
