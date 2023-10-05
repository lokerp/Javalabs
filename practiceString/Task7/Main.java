package Task7;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final int COSTPERWORD = 10;
        System.out.print("Введите текст телеграммы (только латинские или русские буквы, запятые и пробелы): ");
        String text = scanner.nextLine();

        if (!text.matches("[a-zA-Zа-яА-ЯёЁ ,]+")) {
            System.out.println("Введен некорректный текст!");
            System.exit(-1);
        }

        text = text.replaceAll(" +", " ")
                   .replaceAll(" ?, ?", " comma ")
                   .strip();
        int cost = (int) (Arrays.stream(text.split(" "))
                                            .filter(w -> w.strip().length() > 2).count() * 10);
        System.out.println("Стоимость телеграммы: " + cost);
        System.out.println("Вид телеграммы: " + text);


    }
}