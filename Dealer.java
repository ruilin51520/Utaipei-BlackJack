public class Dealer {
    private final Hand dealersHand = new Hand();
    private boolean doesBlackjack = false;
    private boolean doesBust = false;

    public Dealer() {}

    public Hand hand() {
        return dealersHand;
    }

    public void startNewGame() {
        dealersHand.getCardsInHand().clear();
        doesBlackjack = false;
        doesBust = false;
    }

    public void setDoesBlackjack() {
        doesBlackjack = true;
    }

    public boolean getDoesBlackjack() {
        return doesBlackjack;
    }

    public void setDoesBust() {
        doesBust = true;
    }

    public boolean getDoesBust() {
        return doesBust;
    }
}
