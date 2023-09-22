package Task4;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        var arr = new Integer[20];

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

        var newArr = Arrays.stream(arr).filter(el -> (el % 10) == 3).sorted(Comparator.reverseOrder()).toArray();
        System.out.println("Новый массив: " + Arrays.toString(newArr));

    }

    public static int getInput() {
        int input = 0;
        input = in.nextInt();
        return input;
    }
}
