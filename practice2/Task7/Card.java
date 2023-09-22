package Task7;

public record Card(CardSuit suit, CardValue value) {
    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", value=" + value +
                '}';
    }
}
