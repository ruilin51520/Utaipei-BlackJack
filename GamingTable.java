public class GamingTable {
    private static int numberOfPlayers = 4;
    private static int numberOfDecks = 4;

    public static void checkGameInfo() {
        System.out.print("Enter the number of players: ");
        String temp = Utilities.input.nextLine();
        numberOfPlayers = Integer.parseInt(temp, 10);
        System.out.print("Enter the number of decks to play the game: ");
        temp = Utilities.input.nextLine();
        numberOfDecks = Integer.parseInt(temp, 10);
        System.out.println();
    }

    private static void printInfo(Hand hand) {
        for (Card card : hand.getCardsInHand()) {
            System.out.printf("%-16s", card.toString());
            if (hand.getCardsInHand().size() > 3)
                if (card.equals(hand.getCardsInHand().get(2)))
                    System.out.println();
        }
        System.out.println();
        System.out.println("The sum of them: " + hand.calculateValue());
    }

    public static void printPlayersInfo(Player player) {
        System.out.println("Player " + player.getPlayerNo() + "'s current cards in hand:");
        printInfo(player.hand());
        System.out.println();
    }

    public static void printDealersInfo(Dealer dealer) {
        System.out.println("Dealer's current cards in hand:");
        printInfo(dealer.hand());
        System.out.println();
    }

    public static void printDealersFaceUpInfo(Dealer dealer) {
        System.out.println("Dealer's current cards in hand:");
        System.out.printf("%-16s", "*** of ***");
        System.out.printf("%-16s", dealer.hand().getCardsInHand().get(1).toString());
        System.out.println();
        System.out.println("The sum of them: " + dealer.hand().calculateFaceUpValue());
        System.out.println();
    }

    public static void announceDealerBlackjack() {
        System.out.println("Dealer blackjacks.\n");
    }

    public static void announceDealerBlackjack(Player player) {
        if (player.hand().calculateValue() == 21)
            System.out.println("Player " + player.getPlayerNo() + " blackjacks too and pushes.\n");
        else
            System.out.println("Player " + player.getPlayerNo() + " doesn't blackjack and loses.\n");
    }

    public static void announceBlackjack(Player player) {
            System.out.println("Player " + player.getPlayerNo() + " blackjacks and wins.\n");
    }

    public static void announcePlayerBust(Player player) {
        System.out.println("Player " + player.getPlayerNo() + " busts and loses.\n");
    }

    public static void announce7CardCharlie(Player player) {
        System.out.println("Player " + player.getPlayerNo() + " has 7-card Charlie and wins.\n");
    }

    public static void announceDealerBust(Player player) {
        System.out.println("Dealer busts, so Player " + player.getPlayerNo() + " wins.");
    }

    public static void announcePlayerHigherThanDealer(Player player) {
        System.out.println("Player " + player.getPlayerNo() + "'s cards in hand is higher than Dealer's and wins.");
    }

    public static void announcePlayerEqualToDealer(Player player) {
        System.out.println("Player " + player.getPlayerNo() + "'s cards in hand is equal to Dealer's and pushes.");
    }

    public static void announcePlayerLowerThanDealer(Player player) {
        System.out.println("Player " + player.getPlayerNo() + "'s cards in hand is lower than Dealer's and loses.");
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public static int getNumberOfDecks() {
        return numberOfDecks;
    }
}
