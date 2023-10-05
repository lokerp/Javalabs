package Task5;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите текст: ");
        String text = scanner.nextLine();

        Pattern pattern = Pattern.compile("-?[0-9]+");
        Matcher matcher = pattern.matcher(text);

        int sum = matcher.results().mapToInt(r ->
                Integer.parseInt(text.substring(r.start(), r.end()))).sum();

        System.out.println("Сумма чисел в тексте: " + sum);
    }
}
