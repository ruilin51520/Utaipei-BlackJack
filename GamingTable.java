public class GamingTable {
    private static int numberOfPlayers = 4;
    private static int numberOfDecks = 4;
    private static boolean gambling;
    private static int accountPrincipal = 1000;

    public static void checkGameInfo() {
        numberOfPlayers = Utilities.askAnInt("Enter the number of players: ", false);
        numberOfDecks = Utilities.askAnInt("Enter the number of decks to play the game: ", false);
        gambling = Utilities.askYesOrNo("Is there a gambling mode enabled? (Y/N)", false);
        if (gambling)
            accountPrincipal = Utilities.askAnInt("Enter the principal of players' account: ", false);
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
        printInfo(player);
        System.out.println();
    }

    public static void printDealersInfo(Dealer dealer) {
        System.out.println("Dealer's current cards in hand:");
        printInfo(dealer);
        System.out.println();
    }

    public static void printDealersFaceUpInfo(Dealer dealer) {
        System.out.println("Dealer's current cards in hand:");
        System.out.printf("%-16s", "*** of ***");
        System.out.printf("%-16s", dealer.getCardsInHand().get(1).toString());
        System.out.println();
        System.out.println("The sum of them: " + dealer.calculateFaceUpValue());
        System.out.println();
    }

    public static void printPaysOffInsurance(Player player) {
        System.out.println("Dealer blackjacks, pays off Player " + player.getPlayerNo() + "'s insurance bet.");
    }

    public static void printTakesInsurance(Player player) {
        System.out.println("Dealer doesn't blackjacks, takes Player " + player.getPlayerNo() + "'s insurance bet.");
    }

    public static void printPlayersBalance(Player player) {
        int amount = player.getAccount().getBalance();
        int difference = player.getAccount().getBalance() - player.getOriginalAmount();
        System.out.print("Player " + player.getPlayerNo() + "'s account balance is $" + amount + ". ");
        System.out.printf("(%+d)\n", difference);
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void notEnoughBalance() {
        System.out.println("You don't have enough balance, please re-bet.");
    }

    public static void notEnoughBalanceForInsurance(Player player) {
        System.out.println("Player " + player.getPlayerNo() + " don't have enough balance for insurance.");
    }

    public static void notEnoughBalanceForSplit(Player player) {
        System.out.println("Player " + player.getPlayerNo() + " don't have enough balance for split.");
    }

    public static void announceDealerBlackjack() {
        System.out.println("Dealer blackjacks.\n");
    }

    public static void announceDealerBlackjack(Player player) {
        if (player.calculateValue() == 21)
            System.out.println("Player " + player.getPlayerNo() + " blackjacks too and pushes.\n");
        else
            System.out.println("Player " + player.getPlayerNo() + " doesn't blackjack and loses.\n");
    }

    public static void announceBlackjack(Player player) {
        System.out.println("Player " + player.getPlayerNo() + " blackjacks and wins.\n");
    }

    public static void announceSurrender(Player player) {
        System.out.println("Player " + player.getPlayerNo() + " surrender, half bet will be back.\n");
    }

    public static void announcePlayerBust(Player player) {
        System.out.println("Player " + player.getPlayerNo() + " busts and loses.\n");
    }

    public static void announce7CardCharlie(Player player) {
        System.out.println("Player " + player.getPlayerNo() + " has 7-card Charlie and wins.\n");
    }

    public static void announceDealerBust(Player player) {
        System.out.println("Dealer busts, Player " + player.getPlayerNo() + " wins.");
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

    public static void announcePlayerBankrupt(Player player) {
        System.out.println("Player " + player.getPlayerNo() + " is bankrupt and removed from the game.");
    }

    public static int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public static int getNumberOfDecks() {
        return numberOfDecks;
    }

    public static boolean getGambling() {
        return gambling;
    }

    public static int getAccountPrincipal() {
        return accountPrincipal;
    }
}
