package Assets;

public class Transaction {
    private String symbol;
    private double price;
    private double amount;

    //#################### SETTERS AND GETTERS ############################################
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    //#################### CONSTRUCTORS ############################################
    public Transaction() {
        this.symbol = "-";
        this.price = 0;
        this.amount = 0;
    }

    public Transaction(String symbol, double price, double amount) {
        this.symbol = symbol;
        this.price = price;
        this.amount = amount;
    }
}
