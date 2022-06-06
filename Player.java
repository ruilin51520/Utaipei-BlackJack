import java.util.LinkedList;
import java.util.List;

public class Player extends Hand{
    private int playerNo;
    private final List<Hand> hands = new LinkedList<>();
    private final Account account = new Account(GamingTable.getAccountPrincipal());
    private int originalAmount, betAmount;
    private boolean insurance = false, surrender = false ,stopHit = false;

    public Player() {}

    public Player(int playerNo) {
        this.playerNo = playerNo;
    }

    public void startNewGame() {
        setStatus(Status.PLAYING);
        getCardsInHand().clear();
        hands.clear();
        insurance = false;
        setDoesBlackjack(false);
        surrender = false;
        stopHit = false;
    }

    public void removed() {
        startNewGame();
        setStatus(Status.BANKRUPT);
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public List<Hand> getHands() {
        return hands;
    }

    public Hand getHand(int index) {
        return hands.get(index);
    }

    public Account getAccount() {
        return account;
    }

    public void setOriginalAmount(int originalAmount) {
        this.originalAmount = originalAmount;
    }

    public int getOriginalAmount() {
        return originalAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setInsurance() {
        insurance = true;
    }

    public boolean getInsurance() {
        return insurance;
    }

    public void setSurrender() {
        this.surrender = true;
    }

    public boolean getSurrender() {
        return surrender;
    }

    public void setStopHit() {
        stopHit = true;
    }

    public boolean getStopHit() {
        return stopHit;
    }
}
