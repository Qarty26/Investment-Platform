package Model.Assets;

public class Transaction {
    private int idTransaction;
    private String symbol;
    private double price;
    private double amount;
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //#################### CONSTRUCTORS ############################################
    public Transaction() {
        this.symbol = "-";
        this.price = 0;
        this.amount = 0;
        this.type = null;
    }

    public Transaction(int idTransaction,String symbol, double price, double amount,String type) {
        this.idTransaction = idTransaction;
        this.symbol = symbol;
        this.price = price;
        this.amount = amount;
        this.type = type;
    }
}
