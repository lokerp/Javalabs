package Task7;

import java.util.ArrayList;
import java.util.HashSet;

public class Poker {
    private static final int CARDDISTRIBUTION = 5;
    private Deck deck;
    private HashSet<Player> players;

    public Poker(HashSet<Player> players) throws Exception {
        this.players = players;
        deck = Deck.getPokerDeck();
        if (players.size() * CARDDISTRIBUTION > deck.getCardsCount()) {
            throw new Exception("Ошибка! Игроков слишком много для игры в покер.");
        }
    }

    public void startGame() {
        System.out.println("Добро пожаловать в покер...");
        System.out.println("-".repeat(10));
        System.out.println("***Раздача карт***");
        distributeCards();
    }

    private void distributeCards() {
        for (Player p : players) {
            for (int i = 0; i < CARDDISTRIBUTION; i++) {
                Card card = deck.giveRandomCard(p);
                System.out.println("Игроку " + p.getID() + " выдана карта: " + card.toString());
            }
            System.out.println("***");
        }
    }
}
