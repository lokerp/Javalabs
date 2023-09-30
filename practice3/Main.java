import java.util.Comparator;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        final int FIRSTGROUPCOUNT = 25;
        final int SECONDGROUPCOUNT = 30;

        Bus firstBus = new Bus(54, 400);
        Bus secondBus = new Bus(45, 10000);

        Bus largestBus = Stream.of(firstBus, secondBus).max(Comparator.comparingInt(Bus::getAvailableSeats)).get();
        Bus remainedBus = firstBus == largestBus ? secondBus : firstBus;

        try {
            largestBus.takePassengers(Integer.max(FIRSTGROUPCOUNT, SECONDGROUPCOUNT));
        }
        catch (Exception e) {
            System.out.println(e.getMessage()  + " " + remainedBus.getID());
            System.exit(-1);
        }
        try {
            remainedBus.takePassengers(Integer.min(FIRSTGROUPCOUNT, SECONDGROUPCOUNT));
        }
        catch (Exception e) {
            System.out.println(e.getMessage() + " " + remainedBus.getID());
            System.exit(-1);
        }
        System.out.println("Группы распределены успешно.");

        System.out.println("Автобус " + firstBus.getID() + " прибыльный: " + firstBus.isProfitable());
        System.out.println("Автобус " + secondBus.getID() + " прибыльный: " + secondBus.isProfitable());
    }
}