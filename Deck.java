import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    private final LinkedList<Card> decks = new LinkedList<>();

    public Deck() {}

    public Deck(int numberOfDecks) {
        for (int i = 0; i < numberOfDecks; i++)
            newDeck();
    }

    private void newDeck() {
        for (Face face : Face.values())
            for (Suit suit : Suit.values())
                decks.add(new Card(face, suit));
    }

    public void shuffle() {
        Collections.shuffle(decks);
    }

    public void gotACard(Card card) {
        decks.add(card);
    }

    public Card dealACard() {
        return decks.poll();
    }

    public List<Card> getCardsInDeck() {
        return decks;
    }
}
