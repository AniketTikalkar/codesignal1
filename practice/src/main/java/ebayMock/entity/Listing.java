package ebayMock.entity;

import java.math.BigInteger;

// the Listing , same itemId can be listed by many users
public class Listing {
    private final BigInteger itemId;
    private int quantity;
    private Amount unitPrice;
    private final String timeStamp;

    public Listing(BigInteger itemId, int quantity, Amount unitPrice, String timeStamp) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.timeStamp = timeStamp;
    }



    public BigInteger getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Amount getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Amount unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

}
