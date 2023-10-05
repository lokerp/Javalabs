package Task6;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите текст: ");
        String text = scanner.nextLine();
        System.out.print("Введите ключевое слово: ");
        String kw = scanner.nextLine();

        Stream<String> sentences = Arrays.stream(text.split("[.!?]"))
                                         .filter(s -> Arrays.stream(s.split(" "))
                                                            .anyMatch(w -> w.matches(kw)))
                                         .map(String::strip);

        System.out.println("Предложения, содержащие ключевое слово:");
        sentences.forEach(System.out::println);

    }
}
