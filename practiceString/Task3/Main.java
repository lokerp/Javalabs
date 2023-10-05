package Task3;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите предложение, в котором слова разделены пробелами и запятыми: ");
        String sentence = scanner.nextLine().strip();
        var count = Arrays.stream(sentence.split("[ ,]"))
                          .filter(x -> x.length() == 3 && !x.matches("[0-9]+"))
                          .count();
        System.out.println("Количество трехбуквенных слов: " + count);
    }
}

