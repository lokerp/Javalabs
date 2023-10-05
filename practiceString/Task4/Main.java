package Task4;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Введите пароль: ");
        String nextLine = scanner.nextLine();

        Password pswd = null;

        try {
            pswd = new Password(nextLine);
        } catch (Exception e) {
            System.out.println(e);
        }
        assert pswd != null;

        if (pswd.isGood())
            System.out.println("Пароль хороший!");
        else {
            System.out.println("Пароль плохой!");
            for (PswdCriteria r : pswd.getBadQualities())
                System.out.println("Не ооответствует критерию: " + r.toString());
        }

    }
}

