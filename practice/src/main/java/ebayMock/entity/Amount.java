package ebayMock.entity;

import ebayMock.enums.Currency;

public class Amount {
    private Currency currency;
    private  int amount;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "currency=" + currency +
                ", amount=" + amount +
                '}';
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Amount(Currency currency, int amount) {
        this.currency = currency;
        this.amount = amount;
    }
}
