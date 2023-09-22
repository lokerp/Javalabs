package Task1;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        var arr = new int[20];

        System.out.println("Заполните массив 20 целыми числами от 0 до 1000");
        for (int i = 0; i < arr.length; i++)
        {
            while (true) {
                System.out.print(i + 1 + ": ");
                try {
                    arr[i] = getInput();
                }
                catch (Exception e) {
                    System.out.println("Ошибка ввода!");
                    in.nextLine();
                }
                if (arr[i] < 0 || arr[i] > 999)
                    System.out.println("Ошибка ввода! Число должно находиться в диапазоне от 0 до 1000");
                else
                    break;
            }
        }

        int count = 0;
        int sum = 0;
        for (int el : arr) {
            if (el < 1000 && el > 99 && isSymmetric(el)) {
                count++;
                sum += el;
            }
        }
        System.out.println("Количество симметричных чисел: " + count);
        System.out.println("Сумма симметричных чисел: " + sum);

    }

    public static int getInput() {
        int input = 0;
        input = in.nextInt();
        return input;
    }

    public static boolean isSymmetric(int num) {
        String str = Integer.toString(num);
        if (str.length() % 2 == 0)
            return false;
        if (str.length() == 1 || str.substring(0, str.length() / 2).equals(str.substring(str.length() / 2 + 1)))
            return true;
        else
            return false;
    }
}
