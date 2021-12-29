public class Card {
    private Face face;
    private Suit suit;

    public Card() {}

    public Card(Face face, Suit suit) {
        this.face = face;
        this.suit = suit;
    }

    public String toString() {
        return face.getName() + " of " + suit.getName();
    }

    public Face getFace() {
        return face;
    }

    public int getFaceAsValue() {
        return face.getValue();
    }

    public Suit getSuit() {
        return suit;
    }
}
