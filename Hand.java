import java.util.LinkedList;

public class Hand {
    public enum Status {WON, PUSHED, LOST, PLAYING, BANKRUPT}

    private Status status = Status.PLAYING;
    private final LinkedList<Card> cards = new LinkedList<>();
    private boolean doesBlackjack = false;

    public Hand() {}

    public void gotACard(Card card) {
        cards.add(card);
    }

    public int calculateValue() {
        int sum = 0;
        int numberOfAce = 0;

        for (Card card : cards) {
            if (card.getFace().equals(Face.ACE)) {
                numberOfAce++;
            } else {
                sum += card.getFaceAsValue();
            }
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

    public void startNewGame() {
        getCardsInHand().clear();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public LinkedList<Card> getCardsInHand() {
        return cards;
    }

    public void setDoesBlackjack(boolean doesBlackjack) {
        this.doesBlackjack = doesBlackjack;
    }

    public boolean getDoesBlackjack() {
        return doesBlackjack;
    }
}
