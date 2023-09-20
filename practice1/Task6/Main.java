package Task6;
import java.util.*;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        var terminal = new Terminal(Map.ofEntries(
                new AbstractMap.SimpleEntry<Banknote, Integer>(new Banknote(1000), 1),
                new AbstractMap.SimpleEntry<Banknote, Integer>(new Banknote(100), 7),
                new AbstractMap.SimpleEntry<Banknote, Integer>(new Banknote(50), 5)
        ));

        System.out.print("Введите денежную сумму для выдачи: ");
        int money = getInput();

        OperationInfo opInfo = terminal.returnMoney(money);

        if (!opInfo.isSucceed())
            System.out.println("Невозможно вернуть деньги имеющимися купюрами!");
        else {
            System.out.println("Деньги можно вернуть следующим образом: ");
            for (Map.Entry<Banknote, Integer> item : opInfo.issuedMoney().entrySet())
                System.out.println(item.getKey().denomination() + ": " + item.getValue());
        }

    }

    public static int getInput() {
        int input = 0;
        try {
            input = in.nextInt();
            if (input < 0) {
                System.out.println("\nОшибка ввода!");
                System.exit(-1);
            }
        }
        catch (Exception e) {
            System.out.println("\nОшибка ввода!");
            System.exit(-1);
        }

        return input;
    }
}
