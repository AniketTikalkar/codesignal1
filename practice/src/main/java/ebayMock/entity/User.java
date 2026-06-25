package ebayMock.entity;


import java.math.BigInteger;

public class User {
    private final BigInteger userId;
    private final String createdTime;
    private Amount balance;

    public User(BigInteger userId, String createdTime, Amount balance) {
        this.userId = userId;
        this.createdTime = createdTime;
        this.balance = balance;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public Amount getBalance() {
        return balance;
    }

    public void setBalance(Amount balance) {
        this.balance = balance;
    }
    public void updateBalanceAmount(int amount){
        this.balance.setAmount(amount);
    }
}
