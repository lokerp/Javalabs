import java.time.LocalDate;
import java.util.*;

public class Reader implements Comparable<Reader> {
    private final String name;
    private final HashMap<Book, Integer> books;
    private final ArrayList<DebtInfo> debts;

    public Reader(String name){
        this.name = name;
        debts = new ArrayList<>();
        books = new HashMap<>();
    }

    public String getName() {
        return name;
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

    public void addDebt(DebtInfo debt) {
        debts.add(debt);
    }

    public void removeBook(Book book) throws Exception {
        if (!books.containsKey(book) || books.getOrDefault(book, 0) == 0) {
            throw new Exception("Ошибка! Данной книги: " + book + " нет у читателя: " + name);
        }
        int newValue = books.get(book) - 1;
        if (newValue <= 0)
            books.remove(book);
        else
            books.put(book, newValue);
    }

    public void removeDebt(DebtInfo debt) throws Exception {
        if (!debts.contains(debt)){
            throw new Exception("Ошибка! Данного долга: " + debt + " нет у читателя: " + name);
        }
        debts.remove(debt);
    }

    public DebtInfo takeBookFromLibrary(Library library, Book book, LocalDate takenDate, LocalDate returnDate) throws Exception {
        return library.giveBook(this, book, takenDate, returnDate);
    }

    public DebtInfo returnBookToLibrary(Library library, DebtInfo debt, LocalDate actualReturnDate) throws Exception {
        return library.acceptBook(this, debt, actualReturnDate);
    }

    @Override
    public int compareTo(Reader o) {
        return name.compareTo(o.name);
    }
}
