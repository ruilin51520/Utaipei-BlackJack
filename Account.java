public class Account {
    private int balance;

    public Account() {}

    public Account(int principal) {
        this.balance = principal;
    }

    public void transIn(int amount) {
        balance += amount;
    }

    public void transOut(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }
}
