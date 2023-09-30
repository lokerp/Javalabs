import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Library {
    private final ArrayList<DebtInfo> debts;
    private final HashMap<Book, Integer> books;

    public Library() {
        books = new HashMap<>();
        debts = new ArrayList<>();
    }

    public ArrayList<DebtInfo> getDebts() {
        return (ArrayList<DebtInfo>) Collections.unmodifiableList(debts);
    }

    public HashMap<Book, Integer> getBooks() {
        return (HashMap<Book, Integer>) Collections.unmodifiableMap(books);
    }

    public void addBook(Book book) {
        books.put(book, books.getOrDefault(book, 0) + 1);
    }

    public void removeBook(Book book) throws Exception {
        if (!books.containsKey(book) || books.getOrDefault(book, 0) == 0) {
            throw new Exception("Ошибка! Данной книги: " + book + " нет в библиотеке!");
        }
        int newValue = books.get(book) - 1;
        if (newValue <= 0)
            books.remove(book);
        else
            books.put(book, newValue);
    }

    public DebtInfo giveBook (Reader reader, Book book, LocalDate takenDate, LocalDate returnDate) throws Exception {
        if (books.getOrDefault(book, 0) == 0)
            throw new Exception("Книги " + book + " нет в библиотеке!");
        DebtInfo debt = new DebtInfo(this, reader, book, takenDate, returnDate);
        removeBook(book);
        debts.add(debt);
        reader.addBook(book);
        reader.addDebt(debt);
        return debt;
    }

    public DebtInfo acceptBook(Reader reader, DebtInfo debt, LocalDate actualReturnDate) throws Exception {
        if (!debts.contains(debt)){
            throw new Exception("Читателя " + reader + " нет в списке должников!");
        }
        var book = debt.getBook();
        addBook(book);
        debts.remove(debt);
        reader.removeBook(book);
        reader.removeDebt(debt);
        debt.setWasReturned(true);
        debt.setActualReturnDate(actualReturnDate);
        return debt;
    }
}
