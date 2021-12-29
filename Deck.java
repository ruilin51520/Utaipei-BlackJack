import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> decks = new ArrayList<>();

    public Deck() {
        for (int i = 0; i < 4; i++)
            newDeck();
    }

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

    public Card dealACard() {
        Card temp = decks.get(0);
        decks.remove(0);
        return temp;
    }
}
