package Task6;

public record Banknote(int denomination) implements Comparable<Banknote>
{

    public int compareTo(Banknote other) {
        return Integer.compare(this.denomination, other.denomination());
    }
}
