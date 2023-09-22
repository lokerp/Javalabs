package Task7;

import java.util.ArrayList;
import java.util.random.RandomGenerator;

public class Deck {
    private ArrayList<Card> cards;
    public Deck(ArrayList<Card> initCards){
        cards = initCards;
    }
    public int getCardsCount(){
        return cards.size();
    }
    public Card giveRandomCard(Player player) {
        int ind = RandomGenerator.getDefault().nextInt(cards.size());
        Card card = cards.get(ind);
        player.addCard(card);
        cards.remove(ind);
        return card;
    }
    public static Deck getPokerDeck(){
        final int CARDSCOUNT = 52;
        final var SUITSLIST = new CardSuit[] {
                CardSuit.CLUBS, CardSuit.DIAMONDS, CardSuit.HEARTS, CardSuit.SPADES
        };
        final var VALUESLIST = new CardValue[] {
                CardValue.TWO, CardValue.THREE, CardValue.FOUR,
                CardValue.FIVE, CardValue.SIX, CardValue.SEVEN,
                CardValue.EIGHT, CardValue.NINE, CardValue.TEN,
                CardValue.JACK, CardValue.QUEEN, CardValue.KING, CardValue.ACE };

        return generateDeck(CARDSCOUNT, SUITSLIST, VALUESLIST);
    }

    private static Deck generateDeck(int cardsCount, CardSuit[] suitsList, CardValue[] valuesList) {
        var cards = new ArrayList<Card>(cardsCount);
        for (CardSuit suit : suitsList) {
            for (CardValue val : valuesList) {
                cards.add(new Card(suit, val));
            }
        }
        return new Deck(cards);
    }
}
