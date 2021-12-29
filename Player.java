public class Player {
    public enum Status {WON, PUSHED, LOST, PLAYING}

    private Status status = Status.PLAYING;
    private int playerNo;
    private final Hand playersHand = new Hand();
    private boolean stopHit = false;

    public Player() {}

    public Player(int playerNo) {
        this.playerNo = playerNo;
    }

    public Hand hand() {
        return playersHand;
    }

    public void startNewGame() {
        status = Status.PLAYING;
        playersHand.getCardsInHand().clear();
        stopHit = false;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStopHit() {
        stopHit = true;
    }

    public boolean getStopHit() {
        return stopHit;
    }
}
