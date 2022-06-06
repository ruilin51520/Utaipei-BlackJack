public class Dealer extends Hand{
    private boolean doesBust = false;

    public Dealer() {}

    public void startNewGame() {
        getCardsInHand().clear();
        setDoesBlackjack(false);
        doesBust = false;
    }

    public void setDoesBust() {
        doesBust = true;
    }

    public boolean getDoesBust() {
        return doesBust;
    }
}
