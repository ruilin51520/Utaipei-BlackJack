public enum Face {
    ACE("A", null),
    DEUCE("2", 2),
    THREE("3", 3),
    FOUR("4",4),
    FIVE("5",5),
    SIX("6",6),
    SEVEN("7",7),
    EIGHT("8",8),
    NINE("9",9),
    TEN("10",10),
    JACK("J",10),
    QUEEN("Q",10),
    KING("K",10);

    private final String faceName;
    private final Integer faceValue;

    Face(String faceName, Integer faveValue) {
        this.faceName = faceName;
        this.faceValue = faveValue;
    }

    public String getName() {
        return faceName;
    }

    public int getValue() {
        return faceValue;
    }
}
