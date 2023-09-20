import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Task2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите количество заказываемых стекол площадью 0.25 м^2: ");
        double glassNum = in.nextDouble();
        in.close();

        double glassSqr = .25;
        var vitraji = new Firm("Витражи", 420, 75);
        var stekolshik = new Firm("Стекольщик", 440, 65);
        var master = new Firm("Мастер", 470, 55);

        var lowestCostFirm = Stream.of(vitraji, stekolshik, master)
                                .min(Comparator.comparingDouble(x -> x.CalculateCost(glassSqr, glassNum))).get();
        System.out.println("Лучшее предложение - фирма '" + lowestCostFirm.getName() + "' " +
                "с ценой " + lowestCostFirm.CalculateCost(glassSqr, glassNum) + "р.");
    }
}

class Firm {
    private String name;
    private double glassCostForSqM;
    private double cutCostForGlass;
    public Firm(String name, double glassCostForSqM, double cutCostForGlass) {
        this.name = name;
        this.glassCostForSqM = glassCostForSqM;
        this.cutCostForGlass = cutCostForGlass;
    }

    public String getName() {
        return name;
    }

    public double CalculateCost(double glassSqr, double glassNum) {
        return glassNum * (glassCostForSqM * glassSqr + cutCostForGlass);
    }
}