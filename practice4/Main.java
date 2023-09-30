import java.time.LocalDate;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        LocalDate takenDate = LocalDate.of(2023, 3, 1);
        LocalDate returnDate = LocalDate.of(2023, 3, 29);

        Book java = new Book("Java 8. Полное руководство", "Г. Шилдт");
        Book sonata = new Book("Крейцерова соната", "Л. Толстой");

        Library library = new Library();
        library.addBook(java);
        library.addBook(sonata);

        Reader petrov = new Reader("Петров");
        Reader vase4kin = new Reader("Васечкин");

        DebtInfo petrovDebt = null;
        DebtInfo vase4kinDebt = null;

        try {
            petrovDebt = petrov.takeBookFromLibrary(library, java, takenDate, LocalDate.of(2023, 4, 1));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        try {
            vase4kinDebt = vase4kin.takeBookFromLibrary(library, sonata, takenDate, LocalDate.of(2023, 3, 25));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        Stream.of(petrovDebt, vase4kinDebt).forEach(x ->
                System.out.println(x.getReader().getName() + " взял в библиотеке книгу " + x.getBook().toString()
                        + " с " + x.getTakenDate() + " до " + x.getReturnDate())
        );
        System.out.println("_".repeat(10));

        try {
            petrov.returnBookToLibrary(library, petrovDebt, returnDate);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        try {
            vase4kin.returnBookToLibrary(library, vase4kinDebt, returnDate);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        Stream.of(petrovDebt, vase4kinDebt).forEach(x ->
                {
                    var timeDiff = x.getActualReturnDate().minusDays(x.getReturnDate().toEpochDay()).toEpochDay();
                    System.out.println(x.getReader().getName() + " вернул в библиотеку книгу " + x.getBook().toString()
                            + " " + x.getActualReturnDate() + " на " + Math.abs(timeDiff) + " дней "
                            + (timeDiff < 0 ? "раньше" : "позже"));
                }
        );
    }
}