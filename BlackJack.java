public class BlackJack {
    private final Dealer dealer = new Dealer();
    private final static Player[] players = new Player[GamingTable.getNumberOfPlayers()];
    private final Deck decks = new Deck(GamingTable.getNumberOfDecks());
    private final Deck wastes = new Deck();

    public BlackJack() {
        for (int i = 0; i < GamingTable.getNumberOfPlayers(); i++)
            players[i] = new Player(i + 1);
    }

    public void start() {
        if (GamingTable.getGambling()) placeBets();
        decks.shuffle();
        dealCards();
        GamingTable.printDealersFaceUpInfo(dealer);
        Utilities.sleepOneSecond();
        if (GamingTable.getGambling()) askInsurance();
        checkDealerBlackjack();
        if (GamingTable.getGambling()) settleInsurance();
        gotBlackjack();
        askHit();
        dealerHit();
        compareValue();
        if (GamingTable.getGambling()) settleBets();
        if (GamingTable.getGambling()) removeBankruptPlayer();
        resetGame();
    }

    private void placeBets() {
        for (Player player : players) {
            while (player.getStatus() != Player.Status.BANKRUPT) {
                int amount = Utilities.askAnInt(
                        "Player " + player.getPlayerNo() + " please enter the amount you want to bet: ", false);
                if (amount <= player.getAccount().getBalance()) {
                    player.setOriginalAmount(player.getAccount().getBalance());
                    player.setBetAmount(amount);
                    break;
                } else {
                    GamingTable.notEnoughBalance();
                }
            }
        }
        GamingTable.printNewLine();
    }

    private void checkDeck() {
        if (decks.getCardsInDeck().size() < GamingTable.getNumberOfDecks() * 52 / 4) {
            wastes.shuffle();
            for (Card card : wastes.getCardsInDeck())
                decks.gotACard(card);
            wastes.getCardsInDeck().clear();
        }
    }

    private void dealCards() {
        for (int i = 0; i < 2; i++) {
            checkDeck();
            for (Player player : players) {
                if (player.getStatus() != Player.Status.BANKRUPT)
                    player.gotACard(decks.dealACard());
            }
            dealer.gotACard(decks.dealACard());
        }
    }

    private void askInsurance() {
        if (dealer.getCardsInHand().get(1).getFace().equals(Face.ACE)) {
            for (Player player : players) {
                if (player.getStatus() != Player.Status.BANKRUPT) {
                    if (player.getAccount().getBalance() > player.getBetAmount() * 1.5) {
                        if (Utilities.askYesOrNo(
                                "Player " + player.getPlayerNo() + ", do you want insurance? (Y/N)", false)) {
                            player.setInsurance();
                        }
                    } else {
                        GamingTable.notEnoughBalanceForInsurance(player);
                    }
                }
            }
            GamingTable.printNewLine();
        }
    }

    private void checkDealerBlackjack() {
        if (dealer.calculateValue() == 21) {
            GamingTable.printDealersInfo(dealer);
            GamingTable.announceDealerBlackjack();
            dealer.setDoesBlackjack(true);
            Utilities.sleepOneSecond();
            for (Player player : players) {
                if (player.getStatus() != Player.Status.BANKRUPT) {
                    GamingTable.printPlayersInfo(player);
                    GamingTable.announceDealerBlackjack(player);
                    if (player.calculateValue() == 21)
                        player.setStatus(Player.Status.PUSHED);
                    else
                        player.setStatus(Player.Status.LOST);
                    Utilities.sleepOneSecond();
                }
            }
        }
    }

    private void settleInsurance() {
        if (dealer.getCardsInHand().get(1).getFace().equals(Face.ACE)) {
            boolean playerInsurance = false;
            for (Player player :players) {
                if (player.getInsurance()) {
                    if (dealer.getDoesBlackjack()) {
                        GamingTable.printPaysOffInsurance(player);
                        player.getAccount().transIn(player.getBetAmount());
                    } else {
                        GamingTable.printTakesInsurance(player);
                        player.getAccount().transOut(player.getBetAmount() / 2);
                    }
                    playerInsurance = true;
                }
            }
            if (playerInsurance) {
                GamingTable.printNewLine();
                Utilities.sleepOneSecond();
            }
        }
    }

    private void gotBlackjack() {
        for (Player player : players) {
            if (player.calculateValue() == 21 && (player.getStatus() == Player.Status.PLAYING)) {
                GamingTable.printPlayersInfo(player);
                GamingTable.announceBlackjack(player);
                player.setDoesBlackjack(true);
                player.setStatus(Player.Status.WON);
                Utilities.sleepOneSecond();
            }
        }
    }

    private void askDouble(Player player) {
        if (player.getStatus() == Player.Status.PLAYING && player.calculateValue() == 11) {
            if (Utilities.askYesOrNo("Do you want to double down? (Y/N)", false)) {
                player.setBetAmount(player.getBetAmount() * 2);
                player.gotACard(decks.dealACard());
                GamingTable.printNewLine();
                GamingTable.printPlayersInfo(player);
                player.setStopHit();
                Utilities.sleepOneSecond();
            }
        }
    }

    private boolean askSplit(Player player) {
        if (player.getCardsInHand().get(0).getFaceAsValue() ==
                player.getCardsInHand().get(1).getFaceAsValue()) {
            GamingTable.printPlayersInfo(player);
            if (GamingTable.getGambling() &&
                    player.getAccount().getBalance() < player.getBetAmount() * 2) {
                GamingTable.notEnoughBalanceForSplit(player);
            } else {
                if (Utilities.askYesOrNo("Do you want to split? (Y/N)", false)) {
                    if (player.getHands().size() == 0) {
                        player.getHands().add(player);
                        player.getCardsInHand().clear();
                    }
                    player.getHands().add(new Hand());
                    player.getHand(1).gotACard(player.getHand(0).getCardsInHand().poll());
                    player.getHand(0).gotACard(decks.dealACard());
                    player.getHand(1).gotACard(decks.dealACard());
                    GamingTable.printNewLine();
                } else {
                    return true;
                }
            }
        }
        return false;
    }


    private void askSurrender(Player player) {
        if (player.getCardsInHand().size() == 2) {
            boolean gamblingSurrender = false;
            if (Utilities.askYesOrNo("Do you want to surrender? (Y/N)", false)) {
                if (GamingTable.getGambling()) {
                    gamblingSurrender = true;
                    GamingTable.printNewLine();
                    GamingTable.announceSurrender(player);
                    Utilities.sleepOneSecond();
                }
                player.setSurrender();
                player.setStatus(Player.Status.LOST);
            }
            if (!gamblingSurrender) GamingTable.printNewLine();
        } else {
            GamingTable.printNewLine();
        }
    }

    private void askHit() {
        for (Player player : players) {
            checkDeck();
            if (player.getStatus() == Player.Status.PLAYING) {
                if (askSplit(player)) {
                    askHit();
                } else {
                    GamingTable.printPlayersInfo(player);
                }
                if (GamingTable.getGambling()) askDouble(player);
                if (!player.getStopHit()) {
                    for (int i = 1; i <= 5; i++) {
                        if (Utilities.askYesOrNo("Do you want to hit? (Y/N)", false)) {
                            player.gotACard(decks.dealACard());
                            GamingTable.printNewLine();
                            GamingTable.printPlayersInfo(player);
                            if (player.calculateValue() > 21) {
                                GamingTable.announcePlayerBust(player);
                                player.setStatus(Player.Status.LOST);
                                Utilities.sleepOneSecond();
                                break;
                            }
                        } else {
                            askSurrender(player);
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
    }

    private void dealerHit() {
        checkDeck();
        if (!dealer.getDoesBlackjack()) {
            boolean stillPlayer = false;
            GamingTable.printDealersInfo(dealer);
            Utilities.sleepOneSecond();
            for (Player player : players) {
                if (player.getStatus() == Player.Status.PLAYING) {
                    stillPlayer = true;
                    break;
                }
            }
            while (dealer.calculateValue() < 17 && stillPlayer) {
                dealer.gotACard(decks.dealACard());
                GamingTable.printDealersInfo(dealer);
                Utilities.sleepOneSecond();
                if (dealer.calculateValue() > 21) {
                    for (Player player : players) {
                        if (player.getStatus() == Player.Status.PLAYING) {
                            GamingTable.announceDealerBust(player);
                            dealer.setDoesBust();
                            player.setStatus(Player.Status.WON);
                        }
                    }
                    GamingTable.printNewLine();
                    Utilities.sleepOneSecond();
                }
            }
        }
    }

    private void compareValue() {
        if (!(dealer.getDoesBlackjack() || dealer.getDoesBust())) {
            boolean playerCompare = false;
            for (Player player : players) {
                if (player.getStatus() != Player.Status.PLAYING)
                    continue;
                if (player.calculateValue() > dealer.calculateValue()) {
                    GamingTable.announcePlayerHigherThanDealer(player);
                    player.setStatus(Player.Status.WON);
                } else if (player.calculateValue() == dealer.calculateValue()) {
                    GamingTable.announcePlayerEqualToDealer(player);
                    player.setStatus(Player.Status.PUSHED);
                } else {
                    GamingTable.announcePlayerLowerThanDealer(player);
                    player.setStatus(Player.Status.LOST);
                }
                playerCompare = true;
            }
            if (playerCompare) {
                GamingTable.printNewLine();
                Utilities.sleepOneSecond();
            }
        }
    }

    private void settleBets() {
        for (Player player : players) {
            if (player.getStatus() == Player.Status.WON) {
                if (player.getDoesBlackjack())
                    player.getAccount().transIn((int) (player.getBetAmount() * 1.5));
                else
                    player.getAccount().transIn(player.getBetAmount());
            } else if (player.getStatus() == Player.Status.LOST) {
                if (player.getSurrender())
                    player.getAccount().transOut(player.getBetAmount() / 2);
                else
                    player.getAccount().transOut(player.getBetAmount());
            }
            if (player.getStatus() != Player.Status.BANKRUPT)
                GamingTable.printPlayersBalance(player);
        }
        GamingTable.printNewLine();
        Utilities.sleepOneSecond();
    }

    private void removeBankruptPlayer() {
        boolean playerBankrupt = false;
        for (Player player : players) {
            if (player.getAccount().getBalance() <= 0 && (player.getStatus() != Player.Status.BANKRUPT)) {
                player.removed();
                GamingTable.announcePlayerBankrupt(player);
                playerBankrupt = true;
            }
        }
        if (playerBankrupt) {
            GamingTable.printNewLine();
            Utilities.sleepOneSecond();
        }
    }

    private void resetGame() {
        for (Player player : players) {
            if (player.getStatus() != Player.Status.BANKRUPT) {
                for (Card card : player.getCardsInHand())
                    wastes.gotACard(card);
                player.startNewGame();
            }
        }
        for (Card card : dealer.getCardsInHand())
            wastes.gotACard(card);
        dealer.startNewGame();
    }
}
