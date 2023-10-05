package Task1;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите слово: ");
        String word = scanner.nextLine().strip();
        if (word.isEmpty() || word.split(" ").length > 1) {
            System.out.println("Ошибка! Введено не слово!");
            System.exit(-1);
        }
        word = word.toLowerCase();
        System.out.println("Новое слово: " + word);
    }
}
