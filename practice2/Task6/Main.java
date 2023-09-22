package Task6;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        long startTime;

        int myCopyTimeInNs;
        int arraysClassCopyTimeInNs;
        int systemClassCopyTimeInNs;

        int arraySize = 100000000;
        var stringArray = new String[arraySize];

        for (int i = 0; i < arraySize; i++) {
            stringArray[i] = "String";
        }

        var newArr = new String[arraySize];

        startTime = System.nanoTime();
        arrCopy(stringArray, newArr);
        myCopyTimeInNs = (int)TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
        System.out.println(newArr.length);

        startTime = System.nanoTime();
        newArr = Arrays.copyOf(stringArray, stringArray.length);
        arraysClassCopyTimeInNs = (int)TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
        System.out.println(newArr.length);


        startTime = System.nanoTime();
        System.arraycopy(stringArray, 0, newArr, 0, stringArray.length);
        systemClassCopyTimeInNs = (int)TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
        System.out.println(newArr.length);

        System.out.println("Время копирования вручную: " + myCopyTimeInNs + " мс.");
        System.out.println("Время копирования с помощью класса Arrays: " + arraysClassCopyTimeInNs + " мс.");
        System.out.println("Время копирования с помощью класса System: " + systemClassCopyTimeInNs + " мс.");
    }

    public static void arrCopy(String[] src, String[] dst) {
        for (int i = 0; i < src.length; i++)
            dst[i] = src[i];
    }
}
