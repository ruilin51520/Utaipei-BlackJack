import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public Hand() {}

    public void gotACard(Card aCard) {
        cards.add(aCard);
    }

    public int calculateValue() {
        int sum = 0;
        int numberOfAce = 0;

        for (Card card : cards) {
            if (card.getFace().equals(Face.ACE)) {
                numberOfAce++;
                continue;
            }
            sum += card.getFaceAsValue();
        }

        if (numberOfAce > 0) {
            if (sum + 11 + (numberOfAce - 1) <= 21)
                sum = sum + 11 + (numberOfAce - 1);
            else
                sum = sum + numberOfAce;
        }

        return sum;
    }

    public String calculateFaceUpValue() {
        if (!cards.get(0).getFace().equals(Face.ACE))
            return "n + " + (calculateValue() - cards.get(0).getFaceAsValue());
        else if (cards.get(0).getFace().equals(Face.ACE) && cards.get(1).getFace().equals(Face.ACE))
            return "n + " + (calculateValue() - 1);
        else
            return "n + " + (calculateValue() - 11);
    }

    public List<Card> getCardsInHand() {
        return cards;
    }
}
