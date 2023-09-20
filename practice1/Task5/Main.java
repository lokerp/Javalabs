package Task5;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите наименьший номер: ");
        int smallestNum = getInput();
        System.out.print("Введите наибольший номер: ");
        int largestNum = getInput();

        if (largestNum > 999999) {
            System.out.println("\nОшибка. Наибольшего номера билета не существует!");
            System.exit(-1);
        }

        if (smallestNum > largestNum) {
            System.out.println("\nОшибка. Номер наименьшего билета больше номера наибольшего!");
            System.exit(-1);
        }

        int luckyTicketsCount = 0;

        for (int i = smallestNum; i <= largestNum; i++) {
            String strNum = Integer.toString(i);
            strNum = "0".repeat(6 - strNum.length()) + strNum;

            int startSum = 0;
            int endSum = 0;
            for (int j = 0; j < 3; j++)
                startSum += Character.getNumericValue(strNum.charAt(j));
            for (int j = 3; j < 6; j++)
                endSum += Character.getNumericValue(strNum.charAt(j));

            if (startSum == endSum)
                luckyTicketsCount++;
        }

        System.out.println("\nКоличество счастливых билетов: " + luckyTicketsCount);
    }

    public static int getInput() {
        int input = 0;
        try {
            input = in.nextInt();
            if (input < 0) {
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
