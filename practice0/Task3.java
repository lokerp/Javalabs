import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Task3 {

    public static void main(String[] args) {
        var meat = new Item("Мясо", Units.Kg, 285.5, 0);
        var bread = new Item("Хлеб", Units.Piece, 25.4, 0);
        var cucumber = new Item("Огурец", Units.Kg, 40, 0);
        var tomato = new Item("Помидор", Units.Kg, 51, 0);
        var gas = new Item("Бензин", Units.Liter, 43.6, 0);

        var items = new Item[] { meat, bread, cucumber, tomato, gas };
        double totalCost = 0;

        int personsCount = 3;
        var persons = new Person[3];
        for (int i = 0; i < personsCount; i++)
            persons[i] = new Person(i, null);

        Scanner in = new Scanner(System.in);
        for (int i = 0; i < items.length; i++) {
            System.out.print("Введите количество (" + items[i].getUnit().getUnitName() + ") "
                    + items[i].getName() + " которое хотите купить: ");
            items[i].setCount(in.nextDouble());
            totalCost += items[i].getCost();
        }
        in.close();
        // 1
        double avgCost = totalCost / personsCount;
        System.out.println("|Если расходы делятся поровну, то каждый должен потратить " + avgCost + " р.");
        // 2
        System.out.println("|Иначе");
        persons[0].addItem(meat);
        persons[1].setItems(new ArrayList<Item>(List.of(bread, cucumber, tomato)));
        persons[2].addItem(gas);
        for (Person person: persons) {
            System.out.println("Человек [" + person.getID() + "] потратил " + person.getCapital() + " р.");
        }
        System.out.println("-------------------");

        for (int i = 0; i < persons.length; i++) {
            if (persons[i].getCapital() > avgCost) {
                for (int j = 0; j < persons.length; j++) {
                    if (persons[i].getCapital() <= avgCost)
                        break;
                    if (i == j || persons[j].getCapital() >= avgCost)
                        continue;
                    double credit = Math.min(persons[i].getCapital() - avgCost,
                                             avgCost - persons[j].getCapital());
                    System.out.println("Человек [" + persons[j].getID() + "] должен человеку ["
                            + persons[i].getID() + "] " + credit + " р." );
                }
            }
        }
    }
}

enum Units {
    Piece("Штуки"),
    Kg("Килограммы"),
    Liter("Литры");
    private final String unitName;
    Units(String unitName) {
        this.unitName = unitName;
    }
    public String getUnitName(){
        return unitName;
    }
}

class Person {
    private int ID;
    private ArrayList<Item> items;
    private double capital;

    public Person(int ID, ArrayList<Item> items){
        this.ID = ID;
        if (items == null)
            this.items = new ArrayList<>();
        else
            this.items = items;
    }

    public void addItem(Item item) {
        for (Item it : items) {
            if (it.getName().compareTo(item.getName()) == 0) {
                it.setCount(it.getCount() + item.getCount());
                return;
            }
        }
        items.add(item);
        capital += item.getCost();
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
        for (Item item : items) {
            capital += item.getCost();
        }
    }

    public double getCapital() {
        return capital;
    }

    public int getID() {
        return ID;
    }
}

class Item {
    private String name;
    private Units unit;
    private double costForUnit;
    private double count;
    private double cost;

    private void calculateCost() {
        this.cost = count * costForUnit;
    }

    public Item(String name, Units unit, double costForUnit, double count) {
        this.name = name;
        this.unit = unit;
        this.costForUnit = costForUnit;
        this.count = count;
        calculateCost();
    }

    public double getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public Units getUnit(){
        return unit;
    }

    public void setCount(double count) {
        this.count = count;
        calculateCost();
    }

    public double getCount(){
        return count;
    }
}