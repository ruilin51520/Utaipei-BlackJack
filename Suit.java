public enum Suit {
    SPADES("Spades"),
    HEARTS("Hearts"),
    DIAMONDS("Diamonds"),
    CLUBS("Clubs");

    private String suitName;

    Suit() {}

    Suit(String suitName) {
        this.suitName = suitName;
    }

    public String getName() {
        return suitName;
    }
}
