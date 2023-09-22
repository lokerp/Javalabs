package Task7;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Objects;

public class Player {
    private final int ID;
    private ArrayList<Card> cards;
    public Player(int ID){
        this.ID = ID;
        cards = new ArrayList<>(5);
    }
    public int getID() {
        return ID;
    }
    public void addCard(Card card){
        cards.add(card);
    }
    public ArrayList<Card> getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return ID == player.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
