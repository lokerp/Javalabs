package Task7;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int playerNum = 0;
        while (playerNum == 0){
            System.out.print("Введите количество игроков в покер: ");
            playerNum = getNaturalInput();
        }

        var players = new HashSet<Player>(playerNum);
        for (int i = 0; i < playerNum; i++){
            players.add(new Player(i + 1));
        }

        Poker poker = null;
        try {
            poker = new Poker(players);
        }
        catch (Exception ex){
            System.out.print(ex.getMessage());
            System.exit(-1);
        }
        poker.startGame();
    }

    public static int getNaturalInput() {
        int input = 0;
        try {
            input = in.nextInt();
            if (input <= 0) {
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
