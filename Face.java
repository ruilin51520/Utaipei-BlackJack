public enum Face {
    ACE("A", 1),
    DEUCE("2", 2),
    /*THREE("3", 3),
    FOUR("4",4),
    FIVE("5",5),
    SIX("6",6),
    SEVEN("7",7),
    EIGHT("8",8),
    NINE("9",9),
    TEN("10",10),
    JACK("J",10),
    QUEEN("Q",10),
    KING("K",10)*/;

    private String faceName;
    private int faceValue;

    Face() {}

    Face(String faceName, int faceValue) {
        this.faceName = faceName;
        this.faceValue = faceValue;
    }

    public String getName() {
        return faceName;
    }

    public int getValue() {
        return faceValue;
    }
}
