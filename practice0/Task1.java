import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите цену товара: ");
        double num = in.nextDouble();
        in.close();
        num *= .13;
        System.out.println("Цена со скидкой: " + num);
    }
}