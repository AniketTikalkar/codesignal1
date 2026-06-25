package ebayMock.enums;

public enum Currency {
    USD("USD");
    final String currencyCode;

    Currency(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }
}
