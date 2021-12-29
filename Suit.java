public enum Suit {
    SPADES("Spades"),
    HEARTS("Hearts"),
    DIAMONDS("Diamonds"),
    CLUBS("Clubs");

    private final String suitName;

    Suit(String suitName) {
        this.suitName = suitName;
    }

    public String getName() {
        return suitName;
    }
}
