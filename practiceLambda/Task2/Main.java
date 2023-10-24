package Task2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите текст: ");
        StringBuilder sb = new StringBuilder();
        while (true) {
            var line = scanner.nextLine();
            if (line.isEmpty())
                break;
            sb.append(line).append(" ");
        }
        String text = sb.toString();

        var words = text.replaceAll("\\d", "").split("[ ,\n]+");

        System.out.print("Количество слов, оканчивающихся на 'es': ");
        var esWords = Arrays.stream(words).filter(w -> w.endsWith("es")).count();
        System.out.println(esWords);

        System.out.println("Слова, упорядоченные по возрастанию длины:");
        Arrays.stream(words).sorted(Comparator.comparingInt(String::length))
                            .forEach(System.out::println);
    }
}
