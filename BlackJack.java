public class BlackJack {
    private final Dealer dealer = new Dealer();
    private final Player[] players = new Player[GamingTable.getNumberOfPlayers()];
    private final Deck decks = new Deck(GamingTable.getNumberOfDecks());

    public BlackJack() {
        for (int i = 0; i < GamingTable.getNumberOfPlayers(); i++)
            players[i] = new Player(i + 1);
    }

    public void start() {
        decks.shuffle();
        dealCard();
        GamingTable.printDealersFaceUpInfo(dealer);
        Utilities.sleepOneSecond();
        checkDealerBlackjack();
        gotBlackjack();
        askHit();
        dealerHit();
        compareValue();
        resetGame();
    }

    private void dealCard() {
        for (int i = 0; i < 2; i++) {
            for (Player player : players)
                player.hand().gotACard(decks.dealACard());
            dealer.hand().gotACard(decks.dealACard());
        }
    }

    private void checkDealerBlackjack() {
        if (dealer.hand().calculateValue() == 21) {
            GamingTable.printDealersInfo(dealer);
            GamingTable.announceDealerBlackjack();
            dealer.setDoesBlackjack();
            Utilities.sleepOneSecond();
            for (Player player : players) {
                GamingTable.printPlayersInfo(player);
                GamingTable.announceDealerBlackjack(player);
                if (player.hand().calculateValue() == 21)
                    player.setStatus(Player.Status.PUSHED);
                else
                    player.setStatus(Player.Status.LOST);
                Utilities.sleepOneSecond();
            }
        }
    }

    private void gotBlackjack() {
        for (Player player : players) {
            if (player.hand().calculateValue() == 21 && (player.getStatus() == Player.Status.PLAYING)) {
                GamingTable.printPlayersInfo(player);
                GamingTable.announceBlackjack(player);
                player.setStatus(Player.Status.WON);
                Utilities.sleepOneSecond();
            }
        }
    }

    private void askHit() {
        for (Player player : players) {
            if (!player.getStopHit() && (player.getStatus() == Player.Status.PLAYING)) {
                GamingTable.printPlayersInfo(player);
                for (int i = 1; i <= 5; i++) {
                    if (Utilities.askYesOrNo("Do you want to hit? (Y/N)")) {
                        player.hand().gotACard(decks.dealACard());
                        GamingTable.printPlayersInfo(player);
                        if (player.hand().calculateValue() > 21) {
                            GamingTable.announcePlayerBust(player);
                            player.setStatus(Player.Status.LOST);
                            Utilities.sleepOneSecond();
                            break;
                        }
                    } else {
                        player.setStopHit();
                        break;
                    }
                    if (i == 5) {
                        GamingTable.announce7CardCharlie(player);
                        player.setStatus(Player.Status.WON);
                        Utilities.sleepOneSecond();
                    }
                }
            }
        }
    }

    private void dealerHit() {
        if (!dealer.getDoesBlackjack()) {
            GamingTable.printDealersInfo(dealer);
            Utilities.sleepOneSecond();
            while (dealer.hand().calculateValue() < 17) {
                dealer.hand().gotACard(decks.dealACard());
                GamingTable.printDealersInfo(dealer);
                Utilities.sleepOneSecond();
                if (dealer.hand().calculateValue() > 21) {
                    for (Player player : players) {
                        if (player.getStatus() == Player.Status.PLAYING) {
                            GamingTable.announceDealerBust(player);
                            dealer.setDoesBust();
                            player.setStatus(Player.Status.WON);
                        }
                    }
                    GamingTable.printNewLine();
                }
            }
        }
    }

    private void compareValue() {
        if (!(dealer.getDoesBlackjack() || dealer.getDoesBust())) {
            for (Player player : players) {
                if (player.getStatus() != Player.Status.PLAYING)
                    continue;
                if (player.hand().calculateValue() > dealer.hand().calculateValue()) {
                    GamingTable.announcePlayerHigherThanDealer(player);
                    player.setStatus(Player.Status.WON);
                } else if (player.hand().calculateValue() == dealer.hand().calculateValue()) {
                    GamingTable.announcePlayerEqualToDealer(player);
                    player.setStatus(Player.Status.PUSHED);
                } else {
                    GamingTable.announcePlayerLowerThanDealer(player);
                    player.setStatus(Player.Status.LOST);
                }
            }
            GamingTable.printNewLine();
        }
    }

    private void resetGame() {
        for (Player player : players)
            player.startNewGame();
        dealer.startNewGame();
    }
}
