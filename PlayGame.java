public class PlayGame {
    public static void main(String[] args) {
        GamingTable.checkGameInfo();
        BlackJack game = new BlackJack();
        do {
            game.start();
        } while (Utilities.askYesOrNo("Whether to continue the game? (Y/N)", true));
    }
}
