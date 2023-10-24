package Task1;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] diary = {-2, -5, -2, -4, 3, -6, -2,
                -1, 5, 1, 1, 0, -1, 0, 3, -1, 2, 5, 2, 4, 4, 0, 6, 1, 4, 6, -1, 2, 4, 7, 11};

        System.out.print("Количество дней с отрицательной температурой: ");
        var coldDaysNum = (int) Arrays.stream(diary).filter(d -> d < 0).count();
        System.out.println(coldDaysNum);

        System.out.print("Были дни, когда температура оказалась выше 10 градусов: ");
        var wasWarmDays = Arrays.stream(diary).filter(d -> d > 10).count() != 0;
        System.out.println(wasWarmDays);

        System.out.print("Максимальная температура в первую неделю марта: ");
        var maxTempInMarchBeginning = Arrays.stream(diary).limit(7).max().orElseThrow();
        System.out.println(maxTempInMarchBeginning);

    }
}
