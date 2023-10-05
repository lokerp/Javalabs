package Task2;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите зашифрованный текст, в котором слова разделены пробелами и записаны наоборот: ");
        String text = scanner.nextLine().strip();
        StringBuilder decryptedTextSB = new StringBuilder();
        Arrays.stream(text.split(" ")).forEach(x -> decryptedTextSB.insert(0, " " + x));
        String decryptedText = decryptedTextSB.reverse().toString().stripTrailing();
        System.out.println("Расшифрованный текст: " + decryptedText);
    }
}
