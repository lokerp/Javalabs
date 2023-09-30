import java.time.LocalDate;

public class DebtInfo
{
    private boolean wasReturned;
    private final Library library;
    private final Reader reader;
    private final Book book;
    private final LocalDate takenDate;
    private final LocalDate returnDate;
    private LocalDate actualReturnDate;

    public DebtInfo(Library library, Reader reader, Book book, LocalDate takenDate, LocalDate returnDate) {
        wasReturned = false;
        this.library = library;
        this.reader = reader;
        this.book = book;
        this.takenDate = takenDate;
        this.returnDate = returnDate;
        actualReturnDate = null;
    }

    public boolean isWasReturned() {
        return wasReturned;
    }

    public Library getLibrary() {
        return library;
    }

    public Reader getReader() {
        return reader;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getTakenDate() {
        return takenDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public void setWasReturned(boolean wasReturned) {
        this.wasReturned = wasReturned;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }
}
